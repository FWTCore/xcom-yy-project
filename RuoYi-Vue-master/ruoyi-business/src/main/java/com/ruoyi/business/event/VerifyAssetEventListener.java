package com.ruoyi.business.event;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.business.service.VerifyAssetService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * VerifyAssetEventListener
 *
 * @author xcom
 * @date 2025/12/21
 */

@Slf4j
@Component
public class VerifyAssetEventListener {

    @Resource
    private VerifyAssetService verifyAssetService;

    @Async
    @EventListener
    public void handle(VerifyAssetEvent event) {
        if (ObjectUtils.isEmpty(event) || ObjectUtils.isEmpty(event.getProjectId())) {
            return;
        }
        // 手动设置安全上下文
        SecurityContextHolder.setContext(event.getSecurityContext());
        log.info("VerifyAssetEventListener 处理数据：" + JSON.toJSONString(event));
        verifyAssetService.syncVerifyAsset(event.getProjectId());
    }
}
