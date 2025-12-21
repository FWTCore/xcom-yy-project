package com.ruoyi.business.event;

import lombok.Getter;
import org.springframework.security.core.context.SecurityContext;

/**
 * ProjectAssetDataEvent
 *
 * @author xcom
 * @date 2025/11/30
 */

@Getter
public class ProjectAssetDataEvent extends BaseEvent {

    /**
     * 项目id  OriginalAssetDataEventListener
     */
    private final Long    projectId;
    /**
     * 1：实物；2：账务
     */
    private final Integer bizType;

    public ProjectAssetDataEvent(Object source, Long projectId, Integer bizType, SecurityContext securityContext) {
        super(source, securityContext);
        this.projectId = projectId;
        this.bizType = bizType;
    }
}
