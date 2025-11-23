package com.ruoyi.business.service;

import com.ruoyi.business.domain.entity.AssetDataDO;

/**
 * AssetDataService
 *
 * @author xcom
 * @date 2025/11/18
 */

public interface AssetDataService {

    /**
     * 获取资产数据
     * @param assetId
     * @return
     */
    AssetDataDO getAssetDataByAssetId(Long assetId);

}
