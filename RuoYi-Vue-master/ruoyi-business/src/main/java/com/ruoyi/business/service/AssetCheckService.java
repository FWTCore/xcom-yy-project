package com.ruoyi.business.service;

import com.ruoyi.business.domain.model.AssetData;
import com.ruoyi.business.domain.model.request.AssetCheckBO;
import com.ruoyi.business.domain.model.request.AssetCheckMetricsReqBO;
import com.ruoyi.business.domain.model.response.AssetDataDetailVO;
import com.ruoyi.business.domain.model.response.AssetMetricsVO;
import com.ruoyi.common.core.page.TableDataInfo;

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
     * 获取实物资产列表
     * @param assetCheckBO
     * @return
     */
    TableDataInfo listPhysical(AssetCheckBO assetCheckBO);

    /**
     * 获取实物资产列表
     * @param assetCheckBO
     * @return
     */
    List<AssetDataDetailVO> listPhysicalForMysql(AssetCheckBO assetCheckBO);

    /**
     * 查询账务资产指标
     * @param reqBO
     * @return
     */
    List<AssetMetricsVO> listLedgerMetrics(AssetCheckMetricsReqBO reqBO);

    /**
     * 获取实物资产列表
     * @param assetCheckBO
     * @return
     */
    TableDataInfo listLedger(AssetCheckBO assetCheckBO);
    /**
     * 获取实物资产列表
     * @param assetCheckBO
     * @return
     */
    List<AssetDataDetailVO> listLedgerForMysql(AssetCheckBO assetCheckBO);

}
