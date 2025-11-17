package com.ruoyi.web.controller.business.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 资产报表请求模型
 *
 * @author xcom
 * @date 2025/11/17
 */

@ApiModel(value = "资产报表请求模型")
@Data
public class AssetBordRequest implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;

    @ApiModelProperty(value = "部门id")
    private Long              deptId;
    @ApiModelProperty(value = "部门id")
    private Long              projectId;
    @ApiModelProperty(value = "指标类型:1->名称；2->部门；3->存放地点")
    private Integer           metricsType;
    @ApiModelProperty(value = "是否降序，默认降序")
    private Boolean           orderFlag;

}
