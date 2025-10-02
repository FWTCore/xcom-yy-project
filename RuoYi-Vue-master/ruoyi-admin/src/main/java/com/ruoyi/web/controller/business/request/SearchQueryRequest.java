package com.ruoyi.web.controller.business.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 模糊搜索请求模型
 *
 * @author xcom
 * @date 2025/9/20
 */

@ApiModel(value = "模糊搜索请求模型")
@Data
public class SearchQueryRequest implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;
    @ApiModelProperty(value = "搜索关键词")
    private String            searchName;
}
