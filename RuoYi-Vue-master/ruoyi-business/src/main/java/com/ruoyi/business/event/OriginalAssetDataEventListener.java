package com.ruoyi.business.event;

import com.ruoyi.business.domain.entity.OriginalAssetDO;
import com.ruoyi.business.domain.entity.OriginalAssetDataDO;
import com.ruoyi.business.domain.model.OriginalAsset;
import com.ruoyi.business.domain.model.convert.OriginalAssetDataConvert;
import com.ruoyi.business.domain.model.response.AssetMetricsVO;
import com.ruoyi.business.service.MetricsService;
import com.ruoyi.business.service.OriginalAssetDataService;
import com.ruoyi.business.service.OriginalAssetService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 原始资产事件监听
 *
 * @author xcom
 * @date 2025/11/23
 */

@Slf4j
@Component
public class OriginalAssetDataEventListener {

    @Resource
    private OriginalAssetDataService originalAssetDataService;

    @Resource
    private OriginalAssetService     originalAssetService;
    @Resource
    private MetricsService           metricsService;

    /**
     * 异步执行
     *
     * @param event
     */
    //    @Async
    //    @EventListener
    public void handle(OriginalAssetDataEvent event) {
        if (ObjectUtils.isEmpty(event) || ObjectUtils.isEmpty(event.getOriginalAssetId())) {
            return;
        }
        OriginalAssetDO originalAssetDO = originalAssetService.selectOriginalAssetById(event.getOriginalAssetId());
        if (ObjectUtils.isEmpty(originalAssetDO)) {
            return;
        }
        OriginalAssetDataDO originalAssetData = originalAssetDataService
            .getOriginalAssetDataByOriginalAssetId(event.getOriginalAssetId());
        if (ObjectUtils.isEmpty(originalAssetData)) {
            originalAssetData = new OriginalAssetDataDO();
        }
        originalAssetData = OriginalAssetDataConvert.INSTANCE.toOriginalAssetDataDO(originalAssetDO);
        // 先保存，后面更新数量
        originalAssetDataService.addOriginalAssetData(originalAssetData);

        // 获取统计
        try {

            AssetMetricsVO assetNameMetrics = originalAssetService.getAssetNameMetrics(originalAssetData.getProjectId(),
                originalAssetData.getAssetName());
            if (ObjectUtils.isEmpty(assetNameMetrics)) {
                assetNameMetrics = new AssetMetricsVO();
                assetNameMetrics.setTotalCount(0);
                assetNameMetrics.setCheckCount(0);
            }
            originalAssetDataService.updateAssetNameMetrics(originalAssetData.getProjectId(),
                originalAssetData.getAssetName(), assetNameMetrics.getTotalCount(), assetNameMetrics.getCheckCount());
        } catch (Exception exception) {

        }

        try {
            AssetMetricsVO locationMetrics = originalAssetService.getLocationMetrics(originalAssetData.getProjectId(),
                originalAssetData.getLocationId());
            if (ObjectUtils.isEmpty(locationMetrics)) {
                locationMetrics = new AssetMetricsVO();
                locationMetrics.setTotalCount(0);
                locationMetrics.setCheckCount(0);
            }
            originalAssetDataService.updateLocationMetrics(originalAssetData.getProjectId(),
                originalAssetData.getLocationId(), locationMetrics.getTotalCount(), locationMetrics.getCheckCount());

        } catch (Exception exception) {

        }
        try {
            AssetMetricsVO usingDeptMetrics = originalAssetService.getUsingDeptMetrics(originalAssetData.getProjectId(),
                originalAssetData.getUsingDeptId());
            if (ObjectUtils.isEmpty(usingDeptMetrics)) {
                usingDeptMetrics = new AssetMetricsVO();
                usingDeptMetrics.setTotalCount(0);
                usingDeptMetrics.setCheckCount(0);
            }
            originalAssetDataService.updateUsingDeptMetrics(originalAssetData.getProjectId(),
                originalAssetData.getUsingDeptId(), usingDeptMetrics.getTotalCount(), usingDeptMetrics.getCheckCount());

        } catch (Exception exception) {

        }

    }

    /**
     * 异步执行
     *
     * @param event
     */
    @Async
    @EventListener
    public void handleMysql(OriginalAssetDataEvent event) {
        if (ObjectUtils.isEmpty(event)) {
            return;
        }
        // 手动设置安全上下文
        SecurityContextHolder.setContext(event.getSecurityContext());
        if (ObjectUtils.isNotEmpty(event.getOriginalAssetId())) {
            OriginalAssetDO originalAssetDO = originalAssetService.selectOriginalAssetById(event.getOriginalAssetId());
            if (ObjectUtils.isEmpty(originalAssetDO)) {
                return;
            }
            originalAssetService.updateMatchStatic(originalAssetDO.getProjectId(), originalAssetDO.getOriginalCode());
        }
        if (ObjectUtils.isNotEmpty(event.getProjectId()) && CollectionUtils.isNotEmpty(event.getOriginalCodes())) {
            List<OriginalAssetDO> originalAssetDOList = originalAssetService
                .selectOriginalAssetList(event.getProjectId(), event.getOriginalCodes());
            if (CollectionUtils.isEmpty(originalAssetDOList)) {
                return;
            }
            for (OriginalAssetDO originalAssetDO : originalAssetDOList) {
                originalAssetService.updateMatchStatic(originalAssetDO.getProjectId(),
                    originalAssetDO.getOriginalCode());
            }
        }

    }

}
