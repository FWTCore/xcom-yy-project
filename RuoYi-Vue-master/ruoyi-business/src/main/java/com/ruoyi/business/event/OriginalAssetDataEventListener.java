package com.ruoyi.business.event;

import com.ruoyi.business.domain.entity.OriginalAssetDO;
import com.ruoyi.business.domain.entity.OriginalAssetDataDO;
import com.ruoyi.business.domain.model.convert.OriginalAssetDataConvert;
import com.ruoyi.business.domain.model.response.AssetBordMetricsVO;
import com.ruoyi.business.service.OriginalAssetDataService;
import com.ruoyi.business.service.OriginalAssetService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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

    /**
     * 异步执行
     *
     * @param event
     */
    @Async
    @EventListener
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

            AssetBordMetricsVO assetNameMetrics = originalAssetService
                .getAssetNameMetrics(originalAssetData.getProjectId(), originalAssetData.getAssetName());
            if (ObjectUtils.isEmpty(assetNameMetrics)) {
                assetNameMetrics = new AssetBordMetricsVO();
                assetNameMetrics.setTotalCount(0);
                assetNameMetrics.setCheckCount(0);
            }
            originalAssetDataService.updateAssetNameMetrics(originalAssetData.getProjectId(),
                originalAssetData.getAssetName(), assetNameMetrics.getTotalCount(), assetNameMetrics.getCheckCount());
        } catch (Exception exception) {

        }

        try {
            AssetBordMetricsVO locationMetrics = originalAssetService
                .getLocationMetrics(originalAssetData.getProjectId(), originalAssetData.getLocationId());
            if (ObjectUtils.isEmpty(locationMetrics)) {
                locationMetrics = new AssetBordMetricsVO();
                locationMetrics.setTotalCount(0);
                locationMetrics.setCheckCount(0);
            }
            originalAssetDataService.updateLocationMetrics(originalAssetData.getProjectId(),
                originalAssetData.getLocationId(), locationMetrics.getTotalCount(), locationMetrics.getCheckCount());

        } catch (Exception exception) {

        }
        try {
            AssetBordMetricsVO usingDeptMetrics = originalAssetService
                .getUsingDeptMetrics(originalAssetData.getProjectId(), originalAssetData.getUsingDeptId());
            if (ObjectUtils.isEmpty(usingDeptMetrics)) {
                usingDeptMetrics = new AssetBordMetricsVO();
                usingDeptMetrics.setTotalCount(0);
                usingDeptMetrics.setCheckCount(0);
            }
            originalAssetDataService.updateUsingDeptMetrics(originalAssetData.getProjectId(),
                originalAssetData.getUsingDeptId(), usingDeptMetrics.getTotalCount(), usingDeptMetrics.getCheckCount());

        } catch (Exception exception) {

        }

    }

}
