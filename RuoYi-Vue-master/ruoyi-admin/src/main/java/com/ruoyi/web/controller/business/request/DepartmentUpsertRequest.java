package com.ruoyi.web.controller.business.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 部门新增、编辑请求模型
 *
 * @author xcom
 * @date 2025/10/2
 */

@ApiModel(value = "部门新增、编辑请求模型")
@Data
public class DepartmentUpsertRequest implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;
    /**
     * 数据id
     */

    @ApiModelProperty(value = "数据id")
    private Long              id;

    /**
     * 部门名称,;
     */
    @ApiModelProperty(value = "部门名称", required = true)
    @NotEmpty(message = "部门名称不能为空")
    private String            departmentName;
}
