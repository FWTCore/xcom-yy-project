package com.ruoyi.business.event;

import com.ruoyi.business.domain.entity.AssetDO;
import com.ruoyi.business.domain.entity.AssetDataDO;
import com.ruoyi.business.domain.model.convert.AssetDataConvert;
import com.ruoyi.business.domain.model.response.AssetBordMetricsVO;
import com.ruoyi.business.service.AssetDataService;
import com.ruoyi.business.service.AssetService;
import io.netty.util.internal.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * AssetDataEventListener
 *
 * @author xcom
 * @date 2025/11/23
 */

@Slf4j
@Component
public class AssetDataEventListener {

    @Resource
    private AssetDataService assetDataService;

    @Resource
    private AssetService     assetService;

    /**
     * 异步执行
     *
     * @param event
     */
    @Async
    @EventListener
    public void handle(AssetDataEvent event) {
        if (ObjectUtils.isEmpty(event) || ObjectUtils.isEmpty(event.getAssetId())) {
            return;
        }
        AssetDO assetDO = assetService.selectAssetById(event.getAssetId());
        if (ObjectUtils.isEmpty(assetDO)) {
            return;
        }
        AssetDataDO assetData = assetDataService.getAssetDataByAssetId(event.getAssetId());
        if (ObjectUtils.isEmpty(assetData)) {
            assetData = new AssetDataDO();
        }
        assetData = AssetDataConvert.INSTANCE.toAssetDataDO(assetDO);
        // 先保存，后面更新数量
        assetDataService.addAssetData(assetData);

        // 获取统计
        try {
            AssetBordMetricsVO assetNameMetrics = assetService.getAssetNameMetrics(assetData.getProjectId(),
                assetData.getAssetName());
            if (ObjectUtils.isEmpty(assetNameMetrics)) {
                assetNameMetrics = new AssetBordMetricsVO();
                assetNameMetrics.setTotalCount(0);
                assetNameMetrics.setCheckCount(0);
            }
            assetDataService.updateAssetNameMetrics(assetData.getProjectId(), assetData.getAssetName(),
                assetNameMetrics.getTotalCount(), assetNameMetrics.getCheckCount());
        } catch (Exception exception) {

        }

        try {
            AssetBordMetricsVO locationMetrics = assetService.getLocationMetrics(assetData.getProjectId(),
                assetData.getLocationId());
            if (ObjectUtils.isEmpty(locationMetrics)) {
                locationMetrics = new AssetBordMetricsVO();
                locationMetrics.setTotalCount(0);
                locationMetrics.setCheckCount(0);
            }
            assetDataService.updateLocationMetrics(assetData.getProjectId(), assetData.getLocationId(),
                locationMetrics.getTotalCount(), locationMetrics.getCheckCount());
        } catch (Exception exception) {

        }
        try {
            AssetBordMetricsVO usingDeptMetrics = assetService.getUsingDeptMetrics(assetData.getProjectId(),
                assetData.getUsingDeptId());
            if (ObjectUtils.isEmpty(usingDeptMetrics)) {
                usingDeptMetrics = new AssetBordMetricsVO();
                usingDeptMetrics.setTotalCount(0);
                usingDeptMetrics.setCheckCount(0);
            }
            assetDataService.updateUsingDeptMetrics(assetData.getProjectId(), assetData.getLocationId(),
                usingDeptMetrics.getTotalCount(), usingDeptMetrics.getCheckCount());
        } catch (Exception exception) {

        }
    }

}
