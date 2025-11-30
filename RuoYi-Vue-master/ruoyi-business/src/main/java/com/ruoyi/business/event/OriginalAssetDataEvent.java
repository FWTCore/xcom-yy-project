package com.ruoyi.business.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * OriginalAssetDataEvent
 *
 * @author xcom
 * @date 2025/11/23
 */

@Getter
public class OriginalAssetDataEvent extends BaseEvent {

    /**
     * 原始资产id  OriginalAssetDataEventListener
     */
    private final Long         originalAssetId;
    /**
     * projectId
     */
    private final Long         projectId;
    /**
     * originalCodes
     */
    private final List<String> originalCodes;

    public OriginalAssetDataEvent(Object source, Long originalAssetId, Long projectId, List<String> originalCodes,
                                  SecurityContext securityContext) {
        super(source, securityContext);
        this.originalAssetId = originalAssetId;
        this.projectId = projectId;
        this.originalCodes = originalCodes;
    }

}
