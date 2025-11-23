package com.ruoyi.business.service;

import com.ruoyi.business.domain.entity.AssetDataDO;
import com.ruoyi.business.domain.entity.OriginalAssetDataDO;

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
}
