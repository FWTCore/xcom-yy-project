package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.model.request.AssetCheckMetricsReqBO;
import com.ruoyi.business.domain.model.response.AssetMetricsVO;
import com.ruoyi.business.service.AssetCheckService;
import com.ruoyi.business.service.AssetDataService;
import com.ruoyi.business.service.AssetService;
import com.ruoyi.business.service.OriginalAssetDataService;
import com.ruoyi.business.service.OriginalAssetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * AssetCheckService
 *
 * @author xcom
 * @date 2025/11/23
 */

@Service
public class AssetCheckServiceImpl implements AssetCheckService {

    @Resource
    private AssetService             assetService;
    @Resource
    private AssetDataService         assetDataService;
    @Resource
    private OriginalAssetService     originalAssetService;
    @Resource
    private OriginalAssetDataService originalAssetDataService;


    @Override
    public List<AssetMetricsVO> listPhysicalMetrics(AssetCheckMetricsReqBO reqBO) {




        return null;
    }

    @Override
    public List<AssetMetricsVO> listLedgerMetrics(AssetCheckMetricsReqBO reqBO) {
        return null;
    }
}
