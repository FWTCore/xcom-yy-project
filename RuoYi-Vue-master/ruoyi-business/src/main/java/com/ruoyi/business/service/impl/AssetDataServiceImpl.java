package com.ruoyi.business.service.impl;

import com.ruoyi.business.repository.AssetDataRepository;
import com.ruoyi.business.service.AssetDataService;
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



}
