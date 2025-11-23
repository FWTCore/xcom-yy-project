package com.ruoyi.business.event;

import com.ruoyi.business.domain.entity.AssetDO;
import com.ruoyi.business.domain.entity.AssetDataDO;
import com.ruoyi.business.domain.model.convert.AssetDataConvert;
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

        // 获取统计









    }

}
