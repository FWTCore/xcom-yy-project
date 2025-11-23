package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.entity.AssetDataDO;
import com.ruoyi.business.repository.AssetDataRepository;
import com.ruoyi.business.service.AssetDataService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
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
            p.addCriteria(Criteria.where("asset_id").is(assetId));
        });
    }

    @Override
    public boolean addAssetData(AssetDataDO dataDO) {
        if (ObjectUtils.isEmpty(dataDO) || ObjectUtils.isEmpty(dataDO.getAssetId())) {
            return false;
        }
        assetDataRepository.insert(dataDO);
        return true;
    }

    @Override
    public boolean updateAssetNameMetrics(Long projectId, String assetName, Integer totalCount, Integer checkCount) {
        if (ObjectUtils.isEmpty(projectId) || StringUtils.isEmpty(assetName)) {
            return false;
        }
        assetDataRepository.updateByCondition(update -> {
            update.set("asset_name_count", totalCount);
            update.set("asset_name_check_count", checkCount);
        }, query -> {
            Criteria criteria = Criteria.where("project_id").is(projectId);
            criteria.and("asset_name").is(assetName);
            query.addCriteria(criteria);
        });
        return true;
    }

    @Override
    public boolean updateLocationMetrics(Long projectId, Long locationId, Integer totalCount, Integer checkCount) {
        if (ObjectUtils.isEmpty(projectId) || ObjectUtils.isEmpty(locationId)) {
            return false;
        }
        assetDataRepository.updateByCondition(update -> {
            update.set("asset_name_count", totalCount);
            update.set("asset_name_check_count", checkCount);
        }, query -> {
            Criteria criteria = Criteria.where("project_id").is(projectId);
            criteria.and("location_id").is(locationId);
            query.addCriteria(criteria);
        });
        return true;
    }

    @Override
    public boolean updateUsingDeptMetrics(Long projectId, Long usingDeptId, Integer totalCount, Integer checkCount) {
        if (ObjectUtils.isEmpty(projectId) || ObjectUtils.isEmpty(usingDeptId)) {
            return false;
        }
        assetDataRepository.updateByCondition(update -> {
            update.set("asset_name_count", totalCount);
            update.set("asset_name_check_count", checkCount);
        }, query -> {
            Criteria criteria = Criteria.where("project_id").is(projectId);
            criteria.and("using_dept_id").is(usingDeptId);
            query.addCriteria(criteria);
        });
        return true;
    }
}
