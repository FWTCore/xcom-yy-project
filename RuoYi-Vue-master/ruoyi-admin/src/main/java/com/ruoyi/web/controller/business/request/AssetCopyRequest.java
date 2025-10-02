package com.ruoyi.web.controller.business.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 资产复制请求模型
 *
 * @author xcom
 * @date 2025/10/2
 */

@ApiModel(value = "资产复制请求模型")
@Data
public class AssetCopyRequest implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;
    /**
     * 数据id
     */
    @ApiModelProperty(value = "数据id", required = true)
    @NotNull(message = "数据id不能为空")
    private Long              id;

    @ApiModelProperty(value = "复制数量", required = true)
    @NotNull(message = "复制数量不能为空")
    private Integer           copyNum;

}