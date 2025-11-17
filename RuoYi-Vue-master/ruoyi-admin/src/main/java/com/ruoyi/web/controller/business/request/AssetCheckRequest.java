package com.ruoyi.web.controller.business.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 账实核对请求模型
 *
 * @author xcom
 * @date 2025/11/17
 */

@ApiModel(value = "账实核对请求模型")
@Data
public class AssetCheckRequest implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;

    @ApiModelProperty(value = "所属单位id")
    @NotNull(message = "所属单位不能为空")
    private Long              deptId;
    @ApiModelProperty(value = "项目id")
    @NotNull(message = "所属项目不能为空")
    private Long              projectId;

    @ApiModelProperty(value = "排序字段")
    private List<String>      sortFields;

}