package com.ruoyi.quartz.task;

import com.ruoyi.business.event.EventPublisher;
import com.ruoyi.business.service.ProjectService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * AssetMetricsTask
 *
 * @author xcom
 * @date 2025/11/23
 */

@Component("assetMetricsTask")
public class AssetMetricsTask {

    @Resource
    private EventPublisher eventPublisher;

    @Resource
    private ProjectService projectService;

    public void syncMetrics() {
        List<Long> projectIds = projectService.listAllIds();
        if (CollectionUtils.isNotEmpty(projectIds)) {
            for (Long projectId : projectIds) {
                eventPublisher.publishProjectAssetDataEvent(projectId, 1);
                eventPublisher.publishProjectAssetDataEvent(projectId, 2);
                try {
                    // 暂停 1 秒（1000 毫秒）
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }

}
