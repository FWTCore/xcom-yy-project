package com.ruoyi.business.service;

import com.ruoyi.business.domain.entity.AssetDataDO;
import com.ruoyi.business.domain.entity.OriginalAssetDataDO;
import com.ruoyi.business.domain.model.request.AssetBordReqBO;
import com.ruoyi.business.domain.model.request.AssetCheckBO;
import com.ruoyi.business.domain.model.response.AssetMetricsVO;
import com.ruoyi.common.core.page.TableDataInfo;

import java.util.List;

/**
 * OriginalAssetDataService
 *
 * @author xcom
 * @date 2025/11/18
 */

public interface OriginalAssetDataService {

    /**
     * 获取原始资产数据
     * @param originalAssetId
     * @return
     */
    OriginalAssetDataDO getOriginalAssetDataByOriginalAssetId(Long originalAssetId);

    /**
     * 添加数据集
     * @param dataDO
     * @return
     */
    boolean addOriginalAssetData(OriginalAssetDataDO dataDO);

    /**
     * 更新名称指标
     * @param projectId
     * @param assetName
     * @param totalCount
     * @param checkCount
     * @return
     */
    boolean updateAssetNameMetrics(Long projectId, String assetName, Integer totalCount, Integer checkCount);

    /**
     * 更新存放地点指标
     * @param projectId
     * @param locationId
     * @param totalCount
     * @param checkCount
     * @return
     */
    boolean updateLocationMetrics(Long projectId, Long locationId, Integer totalCount, Integer checkCount);

    /**
     * 更新使用地点指标
     * @param projectId
     * @param usingDeptId
     * @param totalCount
     * @param checkCount
     * @return
     */
    boolean updateUsingDeptMetrics(Long projectId, Long usingDeptId, Integer totalCount, Integer checkCount);

    /**
     * 查询资产指标
     * @param assetBordReqBO
     * @return
     */
    List<AssetMetricsVO> listLedgerBord(AssetBordReqBO assetBordReqBO);

    /**
     * 获取账务资产列表
     * @param assetCheckBO
     * @return
     */
    TableDataInfo listLedger(AssetCheckBO assetCheckBO);
}
