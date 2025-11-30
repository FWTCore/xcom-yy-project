package com.ruoyi.quartz.task;

import com.ruoyi.business.event.EventPublisher;
import com.ruoyi.business.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;

/**
 * AssetMetricsTask
 *
 * @author xcom
 * @date 2025/11/23
 */
@Slf4j
@Component("assetMetricsTask")
public class AssetMetricsTask {

    @Resource
    private EventPublisher eventPublisher;

    @Resource
    private ProjectService projectService;

    public void syncMetrics() {
        log.info("assetMetricsTask.syncMetrics===开始");
        long lastId = 0;
        int size = 500;
        boolean hasContinue = true;
        while (hasContinue) {
            List<Long> projectIds = projectService.listAllIds(lastId, size);
            if (CollectionUtils.isEmpty(projectIds) || projectIds.size() < size) {
                hasContinue = false;
            }
            if (CollectionUtils.isNotEmpty(projectIds)) {
                for (Long projectId : projectIds) {
                    eventPublisher.publishProjectAssetDataEvent(projectId, 1);
                    eventPublisher.publishProjectAssetDataEvent(projectId, 2);
                    try {
                        // 暂停 1 秒（1000 毫秒）
                        Thread.sleep(100);
                    } catch (InterruptedException ignored) {
                    }
                }
                // 倒序
                projectIds.sort(Comparator.reverseOrder());
                lastId = projectIds.get(0);
            }
        }
        log.info("assetMetricsTask.syncMetrics===结束");
    }

}
