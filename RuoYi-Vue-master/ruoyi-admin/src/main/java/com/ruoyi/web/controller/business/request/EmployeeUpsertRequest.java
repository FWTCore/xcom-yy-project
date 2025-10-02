package com.ruoyi.web.controller.business.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 员工新增、编辑请求模型
 *
 * @author xcom
 * @date 2025/10/2
 */

@ApiModel(value = "员工新增、编辑请求模型")
@Data
public class EmployeeUpsertRequest implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;
    /**
    * 数据id
    */

    @ApiModelProperty(value = "数据id")
    private Long              id;

    /**
     * 员工名称,;
     */
    @ApiModelProperty(value = "员工名称名称")
    @NotEmpty(message = "员工名称不能为空")
    private String            employeeName;
}
