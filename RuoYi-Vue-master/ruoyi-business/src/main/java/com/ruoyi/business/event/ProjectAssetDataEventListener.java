package com.ruoyi.business.event;

import com.ruoyi.business.domain.entity.OriginalAssetDO;
import com.ruoyi.business.domain.entity.OriginalAssetDataDO;
import com.ruoyi.business.domain.model.convert.OriginalAssetDataConvert;
import com.ruoyi.business.domain.model.response.ProjectDetailVO;
import com.ruoyi.business.service.MetricsService;
import com.ruoyi.business.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * ProjectAssetDataEventListener
 *
 * @author xcom
 * @date 2025/11/30
 */

@Slf4j
@Component
public class ProjectAssetDataEventListener {
    @Resource
    private ProjectService projectService;
    @Resource
    private MetricsService metricsService;

    /**
     * 异步执行
     *
     * @param event
     */
    @Async
    @EventListener
    public void handle(ProjectAssetDataEvent event) {
        if (ObjectUtils.isEmpty(event) || ObjectUtils.isEmpty(event.getProjectId())) {
            return;
        }
        // 手动设置安全上下文
        SecurityContextHolder.setContext(event.getSecurityContext());
        ProjectDetailVO projectDetailVO = projectService.selectProjectById(event.getProjectId());
        if (ObjectUtils.isEmpty(projectDetailVO)) {
            return;
        }
        if (ObjectUtils.isEmpty(event.getBizType()) || event.getBizType().equals(1)) {
            metricsService.upsetPhysicalMetrics(event.getProjectId());
        }
        if (ObjectUtils.isEmpty(event.getBizType()) || event.getBizType().equals(2)) {
            metricsService.upsetLedgerMetrics(event.getProjectId());
        }
    }

}
