package com.ruoyi.quartz.task;

import com.ruoyi.business.service.ProjectService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * AssetMetricsTask
 *
 * @author xcom
 * @date 2025/11/23
 */

@Component("assetMetricsTask")
public class AssetMetricsTask {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Resource
    private ProjectService projectService;

    public void projectStatus() {
        projectService.changeProjectStatus(null);
    }





}
