package com.ruoyi.business.event;

import lombok.Getter;
import org.springframework.security.core.context.SecurityContext;

/**
 * AssetDataEvent
 *
 * @author xcom
 * @date 2025/11/23
 */

@Getter
public class AssetDataEvent extends BaseEvent {

    /**
     * 资产id AssetDataEventListener
     */
    private final Long assetId;

    public AssetDataEvent(Object source, Long assetId, SecurityContext securityContext) {
        super(source, securityContext);
        this.assetId = assetId;
    }
}
