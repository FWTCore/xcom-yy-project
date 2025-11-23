package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.entity.AssetDataDO;
import com.ruoyi.business.repository.AssetDataRepository;
import com.ruoyi.business.service.AssetDataService;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 资产数据mongodb service
 *
 * @author xcom
 * @date 2025/11/18
 */
@Service
public class AssetDataServiceImpl implements AssetDataService {

    @Resource
    private AssetDataRepository assetDataRepository;

    @Override
    public AssetDataDO getAssetDataByAssetId(Long assetId) {
        return assetDataRepository.getOne(p -> {
            p.addCriteria(Criteria.where("assetId").is(assetId));
        });
    }
}
