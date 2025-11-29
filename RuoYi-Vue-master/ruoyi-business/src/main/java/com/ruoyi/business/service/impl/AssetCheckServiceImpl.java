package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.entity.AssetDO;
import com.ruoyi.business.domain.entity.OriginalAssetDO;
import com.ruoyi.business.domain.model.Asset;
import com.ruoyi.business.domain.model.AssetData;
import com.ruoyi.business.domain.model.request.AssetCheckBO;
import com.ruoyi.business.domain.model.request.AssetCheckMetricsReqBO;
import com.ruoyi.business.domain.model.request.AssetCheckRelationalReqBO;
import com.ruoyi.business.domain.model.response.AssetDataDetailVO;
import com.ruoyi.business.domain.model.response.AssetMetricsVO;
import com.ruoyi.business.domain.model.response.OriginalAssetDataDetailVO;
import com.ruoyi.business.service.AssetCheckService;
import com.ruoyi.business.service.AssetDataService;
import com.ruoyi.business.service.AssetService;
import com.ruoyi.business.service.MetricsService;
import com.ruoyi.business.service.OriginalAssetDataService;
import com.ruoyi.business.service.OriginalAssetService;
import com.ruoyi.business.utils.AssetCodeUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.ServiceException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @Resource
    private MetricsService           metricsService;

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
    public List<AssetDataDetailVO> listPhysicalForMysql(AssetCheckBO assetCheckBO) {
        if (CollectionUtils.isNotEmpty(assetCheckBO.getSortFields())) {
            assetCheckBO.getSortFields().sort(Integer::compareTo);
        }
        return metricsService.listPhysical(assetCheckBO);
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

    @Override
    public List<OriginalAssetDataDetailVO> listLedgerForMysql(AssetCheckBO assetCheckBO) {
        if (CollectionUtils.isNotEmpty(assetCheckBO.getSortFields())) {
            assetCheckBO.getSortFields().sort(Integer::compareTo);
        }
        return metricsService.listLedger(assetCheckBO);
    }

    @Override
    public Boolean relational(AssetCheckRelationalReqBO reqBO) {
        List<AssetDO> assetDOList = assetService.selectAssetByIds(reqBO.getPhysicalIds());
        if (CollectionUtils.isEmpty(assetDOList) || assetDOList.size() != reqBO.getPhysicalIds().size()) {
            throw new ServiceException("实物资产有不存在的数据，请刷新页面后再试！");
        }
        if (assetDOList.stream().anyMatch(e -> Objects.equals(e.getMatchStatus(), 1))) {
            throw new ServiceException("实物资产存在已匹配的数据，请刷新页面后再试！");
        }
        if (assetDOList.stream().map(AssetDO::getProjectId).distinct().count() != 1L) {
            throw new ServiceException("实物资产必须属于同一资产，请刷核对后再试！");
        }
        OriginalAssetDO originalAssetDO = originalAssetService.selectOriginalAssetById(reqBO.getLedgerId());
        if (ObjectUtils.isEmpty(originalAssetDO)) {
            throw new ServiceException("账务资产不存在，请刷核对后再试！");
        }
        if (!assetDOList.get(0).getProjectId().equals(originalAssetDO.getProjectId())) {
            throw new ServiceException("实物、账务资产必须属于同一资产，请刷核对后再试！");
        }
        Asset searchAsset = new Asset();
        searchAsset.setDeptId(originalAssetDO.getDeptId());
        searchAsset.setProjectId(originalAssetDO.getProjectId());
        searchAsset.setOriginalCode(originalAssetDO.getOriginalCode());
        List<AssetDO> assetList = assetService.selectAssetList(searchAsset);
        List<String> originalSubCode = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(assetList)) {
            assetList.sort((o1, o2) -> {
                String[] parts1 = o1.getOriginalSubCode().split("-");
                String[] parts2 = o2.getOriginalSubCode().split("-");
                if (parts2.length != parts1.length) {
                    return Integer.compare(parts2.length, parts1.length);
                }
                return parts2[parts2.length - 1].compareTo(parts1[parts1.length - 1]);
            });
            String maxTemporaryCode = assetList.get(0).getOriginalSubCode();
            originalSubCode = AssetCodeUtil.generateAssetCode(maxTemporaryCode, assetDOList.size());
        } else {
            originalSubCode.add(originalAssetDO.getOriginalCode());
            originalSubCode
                .addAll(AssetCodeUtil.generateAssetCode(originalAssetDO.getOriginalCode(), assetDOList.size() - 1));
        }
        int index = 0;
        for (AssetDO assetDO : assetDOList) {
            try {
                assetDO.setOriginalCode(originalAssetDO.getOriginalCode());
                assetDO.setOriginalSubCode(originalSubCode.get(index));
                assetDO.setMatchStatus(1);
                compareChange(assetDO, originalAssetDO);
                assetService.updateAsset(assetDO);
            } catch (Exception exception) {

            }
            index++;
        }
        originalAssetService.updateMatchStatic(originalAssetDO.getOriginalCode());
        return true;
    }

    /**
     * 比较是否变化
     * @param data
     * @param originalAssetDO
     */
    private void compareChange(AssetDO data, OriginalAssetDO originalAssetDO) {
        if (!Objects.equals(data.getLocationId(), originalAssetDO.getLocationId())) {
            data.setPrintStatus(1);
            return;
        }
        if (!Objects.equals(data.getCategoryId(), originalAssetDO.getCategoryId())) {
            data.setPrintStatus(1);
            return;
        }
        if (!StringUtils.equals(data.getBrandName(), originalAssetDO.getBrandName())) {
            data.setPrintStatus(1);
            return;
        }
        if (!StringUtils.equals(data.getAssetName(), originalAssetDO.getAssetName())) {
            data.setPrintStatus(1);
            return;
        }
        if (!StringUtils.equals(data.getSpecification(), originalAssetDO.getSpecification())) {
            data.setPrintStatus(1);
            return;
        }
        if (!Objects.equals(data.getManagedDeptId(), originalAssetDO.getManagedDeptId())) {
            data.setPrintStatus(1);
            return;
        }
        if (!Objects.equals(data.getUsingDeptId(), originalAssetDO.getUsingDeptId())) {
            data.setPrintStatus(1);
            return;
        }
        if (!Objects.equals(data.getManagedEmpId(), originalAssetDO.getManagedEmpId())) {
            data.setPrintStatus(1);
            return;
        }
        if (!Objects.equals(data.getUsingEmpId(), originalAssetDO.getUsingEmpId())) {
            data.setPrintStatus(1);
            return;
        }
    }

}
