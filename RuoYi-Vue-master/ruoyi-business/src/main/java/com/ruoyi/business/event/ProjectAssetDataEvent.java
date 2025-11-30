package com.ruoyi.business.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * ProjectAssetDataEvent
 *
 * @author xcom
 * @date 2025/11/30
 */

@Getter
public class ProjectAssetDataEvent extends ApplicationEvent {

    /**
     * 项目id  OriginalAssetDataEventListener
     */
    private final Long projectId;

    public ProjectAssetDataEvent(Object source, Long projectId) {
        super(source);
        this.projectId = projectId;
    }
}
