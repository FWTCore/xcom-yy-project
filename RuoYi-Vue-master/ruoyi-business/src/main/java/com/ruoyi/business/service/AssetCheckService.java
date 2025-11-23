package com.ruoyi.business.service;

import com.ruoyi.business.domain.model.request.AssetCheckMetricsReqBO;
import com.ruoyi.business.domain.model.response.AssetMetricsVO;

import java.util.List;

/**
 * AssetCheckService
 *
 * @author xcom
 * @date 2025/11/23
 */

public interface AssetCheckService {

    /**
     * 查询实物资产指标
     * @param reqBO
     * @return
     */
    List<AssetMetricsVO> listPhysicalMetrics(AssetCheckMetricsReqBO reqBO);

    /**
     * 查询账务资产指标
     * @param reqBO
     * @return
     */
    List<AssetMetricsVO> listLedgerMetrics(AssetCheckMetricsReqBO reqBO);
}
