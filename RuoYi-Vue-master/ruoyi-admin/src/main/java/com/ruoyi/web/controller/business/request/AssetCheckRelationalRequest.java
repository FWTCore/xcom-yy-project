package com.ruoyi.web.controller.business.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 账实核对关联请求模型
 *
 * @author xcom
 * @date 2025/11/29
 */

@ApiModel(value = "账实核对关联请求模型")
@Data
public class AssetCheckRelationalRequest implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;

    @ApiModelProperty(value = "实物资产id")
    @NotNull(message = "实物资产id不能为空")
    @Size(min = 1, message = "实物资产id至少1个")
    private List<Long>        physicalIds;

    @ApiModelProperty(value = "账务资产id")
    @NotNull(message = "账务资产id不能为空")
    @Size(min = 1, message = "账务资产id至少1个")
    private List<Long>        ledgerIds;

}
