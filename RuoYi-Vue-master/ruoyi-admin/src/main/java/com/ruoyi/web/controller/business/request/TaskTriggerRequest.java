package com.ruoyi.web.controller.business.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 任务触发请求
 *
 * @author xcom
 * @date 2025/12/21
 */

@ApiModel(value = "任务触发请求")
@Data
public class TaskTriggerRequest implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;

    @ApiModelProperty(value = "项目id")
    private Long              projectId;
}
