package com.ruoyi.business.domain.model.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 首页资产统计返回
 *
 * @author xcom
 * @date 2025/10/2
 */

@Data
public class HomeAssetStatsVO implements Serializable {

    /**
     *已采集数
     */
    private BigDecimal collectionCount;
    /**
     *已匹配数
     */
    private BigDecimal matchCount;
}
