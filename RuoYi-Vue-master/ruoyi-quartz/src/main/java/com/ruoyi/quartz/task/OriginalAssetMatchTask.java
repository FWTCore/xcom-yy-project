package com.ruoyi.quartz.task;

import com.ruoyi.business.event.EventPublisher;
import com.ruoyi.business.service.OriginalAssetService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;

/**
 * OriginalAssetMatchTask
 *
 * @author xcom
 * @date 2025/11/23
 */
@Slf4j
@Component("originalAssetMatchTask")
public class OriginalAssetMatchTask {
    @Resource
    private OriginalAssetService originalAssetService;
    @Resource
    private EventPublisher       eventPublisher;

    public void updateMatch() {
        log.info("执行originalAssetMatchTask.updateMatch===开始");
        long lastId = 0;
        int size = 500;
        boolean hasContinue = true;
        while (hasContinue) {
            List<Long> originalAssetIds = originalAssetService.listAllIds(lastId, size);
            if (CollectionUtils.isEmpty(originalAssetIds) || originalAssetIds.size() < size) {
                hasContinue = false;
            }
            if (CollectionUtils.isNotEmpty(originalAssetIds)) {
                for (Long originalAssetId : originalAssetIds) {
                    eventPublisher.publishOriginalAssetDataEvent(originalAssetId, null, null);
                    try {
                        // 暂停 3 秒（3000 毫秒）
                        Thread.sleep(100);
                    } catch (InterruptedException ignored) {
                    }
                }
                // 倒序
                originalAssetIds.sort(Comparator.reverseOrder());
                lastId = originalAssetIds.get(0);
            }
        }
        log.info("执行originalAssetMatchTask.updateMatch===结束");
    }

}
