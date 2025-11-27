package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.model.AssetData;
import com.ruoyi.business.domain.model.request.AssetCheckBO;
import com.ruoyi.business.domain.model.request.AssetCheckMetricsReqBO;
import com.ruoyi.business.domain.model.response.AssetMetricsVO;
import com.ruoyi.business.service.AssetCheckService;
import com.ruoyi.business.service.AssetDataService;
import com.ruoyi.business.service.AssetService;
import com.ruoyi.business.service.OriginalAssetDataService;
import com.ruoyi.business.service.OriginalAssetService;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.ServiceException;
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

        switch (reqBO.getMetricsType()) {
            case 0:
                // 门类
                return assetService.listCategoryMetrics(reqBO);
            case 1:
                // 品牌
                return assetService.listBrandMetrics(reqBO);
            case 2:
                // 名称
                return assetService.listAssetNameMetrics(reqBO);
            case 3:
                // 型号
                return assetService.listSpecificationMetrics(reqBO);
            case 4:
                // 取得时间
                throw new ServiceException("查询指标类型错误");
            case 5:
                //存放地点
                return assetService.listLocationMetrics(reqBO);
            case 6:
                //管理部门
                return assetService.listManagedDeptMetrics(reqBO);
            case 7:
                //使用部门
                return assetService.listUsingDeptMetrics(reqBO);
            case 8:
                //管理人
                return assetService.listManagedEmpMetrics(reqBO);
            case 9:
                //使用人
                return assetService.listUsingEmpMetrics(reqBO);
            default:
                throw new ServiceException("查询指标类型错误");
        }
    }

    @Override
    public TableDataInfo listPhysical(AssetCheckBO assetCheckBO) {
        return assetDataService.listPhysical(assetCheckBO);
    }

    @Override
    public List<AssetMetricsVO> listLedgerMetrics(AssetCheckMetricsReqBO reqBO) {
        switch (reqBO.getMetricsType()) {
            case 0:
                // 门类
                return originalAssetService.listCategoryMetrics(reqBO);
            case 1:
                // 品牌
                return originalAssetService.listBrandMetrics(reqBO);
            case 2:
                // 名称
                return originalAssetService.listAssetNameMetrics(reqBO);
            case 3:
                // 型号
                return originalAssetService.listSpecificationMetrics(reqBO);
            case 4:
                // 取得时间
                return originalAssetService.listObtainTimeMetrics(reqBO);
            case 5:
                //存放地点
                return originalAssetService.listLocationMetrics(reqBO);
            case 6:
                //管理部门
                return originalAssetService.listManagedDeptMetrics(reqBO);
            case 7:
                //使用部门
                return originalAssetService.listUsingDeptMetrics(reqBO);
            case 8:
                //管理人
                return originalAssetService.listManagedEmpMetrics(reqBO);
            case 9:
                //使用人
                return originalAssetService.listUsingEmpMetrics(reqBO);
            default:
                throw new ServiceException("查询指标类型错误");
        }
    }

    @Override
    public TableDataInfo listLedger(AssetCheckBO assetCheckBO) {
        return originalAssetDataService.listLedger(assetCheckBO);
    }
}
