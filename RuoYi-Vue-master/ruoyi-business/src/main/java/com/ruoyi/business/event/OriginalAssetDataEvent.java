package com.ruoyi.business.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * OriginalAssetDataEvent
 *
 * @author xcom
 * @date 2025/11/23
 */

@Getter
public class OriginalAssetDataEvent extends ApplicationEvent {

    /**
     * 原始资产id  OriginalAssetDataEventListener
     */
    private final Long originalAssetId;

    public OriginalAssetDataEvent(Object source, Long originalAssetId) {
        super(source);
        this.originalAssetId = originalAssetId;
    }

}
