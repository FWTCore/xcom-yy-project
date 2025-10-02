package com.ruoyi.web.controller.business.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 品牌请求模型
 *
 * @author xcom
 * @date 2025/9/20
 */

@ApiModel(value = "品牌请求模型")
@Data
public class BrandQueryRequest implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;
    @ApiModelProperty(value = "分类id")
    private Long              categoryId;
    @ApiModelProperty(value = "搜索关键词")
    private String            searchName;
}
