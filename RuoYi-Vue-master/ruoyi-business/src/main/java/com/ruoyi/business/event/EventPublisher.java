package com.ruoyi.business.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * EventPublisher
 *
 * @author xcom
 * @date 2025/11/30
 */
@Component
public class EventPublisher {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishAssetDataEvent(Long assetId) {
        SecurityContext context = SecurityContextHolder.getContext();
        applicationEventPublisher.publishEvent(new AssetDataEvent(this, assetId, context));
    }

    public void publishOriginalAssetDataEvent(Long originalAssetId, Long projectId, List<String> originalCodes) {
        SecurityContext context = SecurityContextHolder.getContext();
        applicationEventPublisher
            .publishEvent(new OriginalAssetDataEvent(this, originalAssetId, projectId, originalCodes, context));
    }

    public void publishProjectAssetDataEvent(Long projectId, Integer bizType) {
        SecurityContext context = SecurityContextHolder.getContext();
        applicationEventPublisher.publishEvent(new ProjectAssetDataEvent(this, projectId, bizType, context));
    }

    public void publishProjectVerifyAssetEventDataEvent(Long projectId, Integer bizType) {
        SecurityContext context = SecurityContextHolder.getContext();
        applicationEventPublisher.publishEvent(new VerifyAssetEvent(this, projectId, bizType, context));
    }

}
