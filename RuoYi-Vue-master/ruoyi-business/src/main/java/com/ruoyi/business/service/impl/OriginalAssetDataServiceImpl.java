package com.ruoyi.business.service.impl;

import com.ruoyi.business.repository.OriginalAssetDataRepository;
import com.ruoyi.business.service.OriginalAssetDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 原始资产数据mongodb service
 *
 * @author xcom
 * @date 2025/11/18
 */

@Service
public class OriginalAssetDataServiceImpl implements OriginalAssetDataService {

    @Resource
    private OriginalAssetDataRepository originalAssetDataRepository;


}
