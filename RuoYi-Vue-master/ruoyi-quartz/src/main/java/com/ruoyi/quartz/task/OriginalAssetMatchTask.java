package com.ruoyi.quartz.task;

import com.ruoyi.business.event.EventPublisher;
import com.ruoyi.business.service.OriginalAssetService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * OriginalAssetMatchTask
 *
 * @author xcom
 * @date 2025/11/23
 */

@Component("originalAssetMatchTask")
public class OriginalAssetMatchTask {
    @Resource
    private OriginalAssetService originalAssetService;
    @Resource
    private EventPublisher       eventPublisher;

    public void updateMatch() {
        List<Long> originalAssetIds = originalAssetService.listAllIds();
        if (CollectionUtils.isNotEmpty(originalAssetIds)) {
            for (Long originalAssetId : originalAssetIds) {
                eventPublisher.publishOriginalAssetDataEvent(originalAssetId, null, null);
                try {
                    // 暂停 3 秒（3000 毫秒）
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }

}
