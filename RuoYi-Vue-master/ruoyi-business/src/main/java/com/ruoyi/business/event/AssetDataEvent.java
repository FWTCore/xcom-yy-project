package com.ruoyi.business.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * AssetDataEvent
 *
 * @author xcom
 * @date 2025/11/23
 */

@Getter
public class AssetDataEvent extends ApplicationEvent {

    /**
     * 资产id AssetDataEventListener
     */
    private final Long assetId;

    public AssetDataEvent(Object source, Long assetId) {
        super(source);
        this.assetId = assetId;
    }
}
