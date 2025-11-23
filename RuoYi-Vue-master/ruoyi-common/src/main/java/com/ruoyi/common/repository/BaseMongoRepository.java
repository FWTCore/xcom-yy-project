package com.ruoyi.common.repository;

import com.ruoyi.common.core.domain.BaseMongoDO;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.function.Consumer;

/**
 * mongodb Repository 基础方法
 *
 * @author xcom
 * @date 2025/11/23
 */

public interface BaseMongoRepository<T extends BaseMongoDO> {
    /**
     * 新增
     *
     * @param entity
     * @return
     */
    T insert(T entity);

    /**
     * 新增
     *
     * @param entityList
     * @return
     */
    List<T> insertAll(List<T> entityList);

    /**
     * 修改(替换)
     *
     * @param entity
     * @return
     */
    T updateById(T entity);

    /**
     * 根据条件更新文档
     *
     * @param updateExpression
     * @param queryExpression
     * @return
     */
    Long updateByCondition(Consumer<Update> updateExpression, Consumer<Query> queryExpression);

    /**
     * 软删除
     *
     * @param queryExpression
     * @return
     */
    Long updateForDelete(Consumer<Query> queryExpression);

    /**
     * 查询列表
     *
     * @param queryExpression
     * @return
     */
    T getOne(Consumer<Query> queryExpression);

    /**
     * 查询列表
     *
     * @param queryExpression
     * @return
     */
    List<T> listData(Consumer<Query> queryExpression);

    /**
     * 物理删除数据
     *
     * @param deleteQueryExpression
     * @return
     */
    Long removeByCondition(Consumer<Query> deleteQueryExpression);

}
