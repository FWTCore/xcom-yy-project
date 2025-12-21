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
 * VerifyAssetTask
 *
 * @author xcom
 * @date 2025/12/21
 */

@Slf4j
@Component("verifyAssetTask")
public class VerifyAssetTask {

    @Resource
    private ProjectService projectService;
    @Resource
    private EventPublisher eventPublisher;

    public void updateMatch() {
        log.info("verifyAssetTask.updateMatch===开始");
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
                    eventPublisher.publishProjectVerifyAssetEventDataEvent(projectId, 1);
                    eventPublisher.publishProjectVerifyAssetEventDataEvent(projectId, 2);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ignored) {
                    }
                }
                // 倒序
                projectIds.sort(Comparator.reverseOrder());
                lastId = projectIds.get(0);
            }
        }
        log.info("verifyAssetTask.updateMatch===结束");
    }

    public void updateMatchById(Long id) {
        log.info("执行verifyAssetTask.updateMatch===开始，参数：" + id);
        eventPublisher.publishProjectVerifyAssetEventDataEvent(id, 1);
        eventPublisher.publishProjectVerifyAssetEventDataEvent(id, 2);
        log.info("执行verifyAssetTask.updateMatch===结束");
    }

}
