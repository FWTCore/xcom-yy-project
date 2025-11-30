package com.ruoyi.quartz.task;

import com.ruoyi.business.service.ProjectService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 定时任务--项目
 *
 * @author xcom
 * @date 2025/11/23
 */

@Component("projectTask")
public class ProjectTask {

    @Resource
    private ProjectService projectService;

    public void changeProjectStatus() {
        projectService.changeProjectStatus(null);
    }



}
