package com.ruoyi.business.model.request;

import com.ruoyi.common.core.domain.BaseEntityDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 采集统计请求模型
 *
 * @author xcom
 * @date 2025/10/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CollectionStatsReqBO extends BaseEntityDO {

    /**
     * 项目id
     */

    private Long          projectId;

    /**
     * 维度类型 1；采集人；2：使用部门；3：存放地点
     */
    private Integer       dimensionType;
    /**
     * 采集开始时间
     */
    private LocalDateTime startTime;
    /**
     * 采集开始时间
     */
    private LocalDateTime endTime;
}
