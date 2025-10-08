package com.ruoyi.web.controller.business.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 物资规格请求模型
 *
 * @author xcom
 * @date 2025/10/2
 */

@ApiModel(value = "物资规格请求模型")
@Data
public class MaterialSpecQueryRequest implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;
    @ApiModelProperty(value = "门类id")
    private Long              categoryId;
    @ApiModelProperty(value = "品牌名称")
    private String            brandName;
    @ApiModelProperty(value = "物资名称")
    private String            materialName;
    @ApiModelProperty(value = "搜索关键词")
    private String            searchName;
}
