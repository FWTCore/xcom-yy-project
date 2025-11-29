package com.ruoyi.business.domain.model.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 看板概览
 *
 * @author xcom
 * @date 2025/11/23
 */

@Data
public class AssetMetricsVO implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;
    /**
     * 指标名称
     */
    private String            metricsId;
    /**
     * 指标名称
     */
    private String            metricsName;
    /**
     * 总数
     */
    private Integer           totalCount;
    /**
     * 核对数
     */
    private Integer           checkCount;

}
