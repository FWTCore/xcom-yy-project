package com.ruoyi.business.model.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 采集统计返回模型
 *
 * @author xcom
 * @date 2025/10/26
 */

@Data
public class CollectionStatsVO implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;

    /**
     * 维度id
     */
    private String            dimensionId;
    /**
     * 维度名称
     */
    private String            dimensionName;
    /**
     * 维度统计数量
     */
    private BigDecimal        dimensionCount;

}
