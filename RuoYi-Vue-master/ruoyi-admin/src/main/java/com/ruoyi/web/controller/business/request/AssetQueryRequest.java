package com.ruoyi.web.controller.business.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ruoyi.business.utils.LenientLocalDateTimeDeserializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 资产请求模型
 *
 * @author xcom
 * @date 2025/10/2
 */

@ApiModel(value = "资产请求模型")
@Data
public class AssetQueryRequest implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;

    @ApiModelProperty(value = "存放地点id")
    private Long              locationId;
    @ApiModelProperty(value = "部门id")
    private Long              deptId;
    @ApiModelProperty(value = "使用部门id")
    private Long              usingDeptId;
    @ApiModelProperty(value = "采集人id")
    private Long              collectorUserId;
    @ApiModelProperty(value = "搜索关键词")
    private String            searchName;

    /**
     * 采集开始时间
     */
    @ApiModelProperty(value = "采集开始时间")
    private String            startTime;

    /**
     * 采集开始时间
     */
    @ApiModelProperty(value = "采集开始时间")
    private String            endTime;

}
