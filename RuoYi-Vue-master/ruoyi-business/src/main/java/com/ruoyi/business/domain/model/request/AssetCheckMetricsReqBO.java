package com.ruoyi.business.domain.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * AssetCheckMetricsReqBO
 *
 * @author xcom
 * @date 2025/11/23
 */
@Data
public class AssetCheckMetricsReqBO implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;

    private Integer           pageNum;
    private Integer           pageSize;
    /**
     * 所属单位id
     */
    private Long              deptId;
    /**
     * 项目id
     */
    private Long              projectId;
    /**
     * 指标类型
     * 0->门类,1->品牌；2->名称；3-> 型号；4-> 取得时间,5->存放地点,6->管理部门,7->使用部门,8->管理人,9->使用人
     */
    private Integer           metricsType;
}
