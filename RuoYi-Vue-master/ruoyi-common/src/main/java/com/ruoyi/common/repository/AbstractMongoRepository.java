package com.ruoyi.common.repository;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.ruoyi.common.core.domain.AggregationMongodbQuery;
import com.ruoyi.common.core.domain.BaseMongoDO;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * AbstractMongoRepository
 *
 * @author xcom
 * @date 2025/11/23
 */
@Service
@Slf4j
public class AbstractMongoRepository<T extends BaseMongoDO> implements BaseMongoRepository<T> {

    protected Class<T> modelType;

    @Resource
    MongoTemplate      mongoTemplate;

    public AbstractMongoRepository() {
        try {
            this.modelType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(),
                AbstractMongoRepository.class);
        } catch (Exception e) {
            log.error("getModelTypeClass happen error", e);
        }
    }

    /**
     * 新增
     *
     * @param entity
     * @return
     */
    @Override
    public T insert(T entity) {
        if (Objects.isNull(entity.getDeleteFlag())) {
            entity.setDeleteFlag(Boolean.FALSE);
        }
        return mongoTemplate.save(entity);
    }

    /**
     * 新增
     *
     * @param entityList
     * @return
     */
    @Override
    public List<T> insertAll(List<T> entityList) {
        entityList.forEach(entity -> {
            if (Objects.isNull(entity.getDeleteFlag())) {
                entity.setDeleteFlag(Boolean.FALSE);
            }
        });
        return (List<T>) mongoTemplate.insertAll(entityList);
    }

    /**
     * 修改(替换)
     *
     * @param entity
     * @return
     */
    @Override
    public T updateById(T entity) {
        Query query = new Query(new Criteria("_id").is(entity.getId()));
        return mongoTemplate.findAndReplace(query, entity);
    }

    /**
     * 根据条件更新文档
     *
     * @param updateExpression
     * @param queryExpression
     * @return
     */
    @Override
    public Long updateByCondition(Consumer<Update> updateExpression, Consumer<Query> queryExpression) {
        Update upsert = new Update();
        Query query = new Query();
        updateExpression.accept(upsert);
        queryExpression.accept(query);
        if (ObjectUtils.isEmpty(upsert) || upsert.getUpdateObject().isEmpty() || ObjectUtils.isEmpty(query)
            || query.getQueryObject().isEmpty()) {
            return 0L;
        }
        LoginUser loginUser = SecurityUtils.getLoginUser();
        upsert.set("updated_time", LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        if (ObjectUtils.isNotEmpty(loginUser)) {
            upsert.set("updated_by_id", loginUser.getUserId());
            upsert.set("updated_by_name", loginUser.getUsername());
        }
        UpdateResult result = mongoTemplate.updateMulti(query, upsert, modelType);
        return result.getModifiedCount();
    }

    /**
     * 软删除
     *
     * @param queryExpression
     * @return
     */
    @Override
    public Long updateForDelete(Consumer<Query> queryExpression) {
        return this.updateByCondition(update -> {
            update.set("delete_flag", Boolean.TRUE);
        }, queryExpression);
    }

    /**
     * 通过id获取对象
     * @param id
     * @return
     */
    public T getDataById(Object id) {
        return mongoTemplate.findById(id, modelType);
    }

    /**
     * 查新单个
     *
     * @param queryExpression 查询表达式
     */
    @Override
    public T getOne(Consumer<Query> queryExpression) {
        Query query = new Query();
        queryExpression.accept(query);
        if (ObjectUtils.isEmpty(query) || query.getQueryObject().isEmpty()) {
            return null;
        }
        return mongoTemplate.findOne(query, modelType);
    }

    /**
     * 查询列表
     *
     * @param queryExpression 查询表达式
     */
    @Override
    public List<T> listData(Consumer<Query> queryExpression) {
        Query query = new Query();
        queryExpression.accept(query);
        if (ObjectUtils.isEmpty(query) || query.getQueryObject().isEmpty()) {
            return new ArrayList<>();
        }
        return mongoTemplate.find(query, modelType);
    }

    /**
     * 聚合查询列表数据
     * @param query
     * @param <R>
     * @return
     */
    @Override
    public <R> List<R> listAggregationData(AggregationMongodbQuery<R> query) {
        // 1. 构建匹配条件（WHERE id=123 and del=0）
        MatchOperation matchStage = Aggregation.match(query.getCriteria());
        // 2. 构建分组来实现 DISTINCT a, b.toArray(new String[0])
        GroupOperation groupStage = Aggregation.group(query.getQueryFields().toArray(new String[0]));
        // 3. 构建投影，从分组结果中提取字段
        ProjectionOperation projectStage = query.getProjectionOperation();
        // 4. 构建排序（ORDER BY b DESC）
        SortOperation sortStage = query.getSortOperation();
        // 5. 构建聚合管道
        LimitOperation limitStage = Aggregation.limit(query.getLimit());
        Aggregation aggregation = Aggregation.newAggregation(matchStage, groupStage, projectStage, sortStage,
            limitStage);
        // 6. 执行查
        AggregationResults<R> results = mongoTemplate.aggregate(aggregation, modelType, query.getRespClazz());
        return results.getMappedResults();
    }

    /**
     * 物理删除数据
     *
     * @param deleteQueryExpression
     */
    @Override
    public Long removeByCondition(Consumer<Query> deleteQueryExpression) {
        Query deleteQuery = new Query();
        //setDeleteFilter(deleteQuery);
        deleteQueryExpression.accept(deleteQuery);
        // 执行删除
        DeleteResult result = mongoTemplate.remove(deleteQuery, modelType);
        return result.getDeletedCount();
    }

    /**
     * 设置过滤软删除条件
     *
     * @param query
     */
    private void setDeleteFilter(Query query) {
        query.addCriteria(Criteria.where("delete_flag").is(Boolean.FALSE));
    }
}
