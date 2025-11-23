package com.ruoyi.common.core.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import java.io.Serializable;
import java.util.List;

/**
 * AggregationMongodbQuery
 *
 * @author xcom
 * @date 2025/11/23
 */

@Data
public class AggregationMongodbQuery<R> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 必须要初始化
     *
     * @param respClass
     */
    public AggregationMongodbQuery(Class<R> respClass) {
        this.respClazz = respClass;
    }

    /**
     * 查询条件
     * WHERE id=123 and del=0
     */
    private Criteria            criteria;
    /**
     * 查询字段
     * 构建分组来实现 DISTINCT a, b
     */
    private List<String>        queryFields;
    /**
     * 构建投影，从分组结果中提取字段
     * ProjectionOperation projectStage = Aggregation.project().and("_id.a").as("a").and("_id.b").as("b").andExclude("_id");
     */
    private ProjectionOperation projectionOperation;

    /**
     * 构建排序（ORDER BY b DESC）
     * SortOperation sortStage = Aggregation.sort(Sort.by(Sort.Direction.DESC, "b"));
     */
    private SortOperation       sortOperation;

    /**
     * 聚合size
     */
    private int                 limit = 10;

    /**
     * 返回对象class
     */
    private Class<R>            respClazz;

}
