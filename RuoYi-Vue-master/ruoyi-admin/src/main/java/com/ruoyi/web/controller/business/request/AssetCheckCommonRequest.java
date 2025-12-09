package com.ruoyi.web.controller.business.request;

import com.ruoyi.common.core.domain.BaseEntityDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 账实核对请求基础模型
 *
 * @author xcom
 * @date 2025/12/9
 */

@ApiModel(value = "账实核对请求基础模型")
@Data
@EqualsAndHashCode(callSuper = true)
public class AssetCheckCommonRequest extends BaseEntityDO {

    @ApiModelProperty(value = "门类")
    private String searCategory;
    @ApiModelProperty(value = "品牌")
    private String searBrand;
    @ApiModelProperty(value = "名称")
    private String searName;
    @ApiModelProperty(value = "规格")
    private String searSpecif;
    @ApiModelProperty(value = "地点")
    private String searLocation;
    @ApiModelProperty(value = "管理部门")
    private String searManagedDept;
    @ApiModelProperty(value = "使用部门")
    private String searUsingDept;
    @ApiModelProperty(value = "管理人")
    private String searManagedEmp;
    @ApiModelProperty(value = "使用人")
    private String searUsingEmp;

}
