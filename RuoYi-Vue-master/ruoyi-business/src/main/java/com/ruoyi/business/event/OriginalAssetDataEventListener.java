package com.ruoyi.business.event;

import com.ruoyi.business.service.OriginalAssetDataService;
import com.ruoyi.business.service.OriginalAssetService;
import lombok.extern.slf4j.Slf4j;
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

    }

}
