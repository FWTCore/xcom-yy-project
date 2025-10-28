package com.ruoyi.web.controller.business.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ruoyi.business.utils.LenientLocalDateTimeDeserializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 采集统计请求模型
 *
 * @author xcom
 * @date 2025/10/26
 */

@ApiModel(value = "采集统计请求模型")
@Data
public class CollectionStatsRequest implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;

    /**
     * 维度类型 1；采集人；2：使用部门；3：存放地点
     */
    @NotNull(message = "维度类型不能为空")
    @ApiModelProperty(value = "维度类型")
    private Integer           dimensionType;

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
