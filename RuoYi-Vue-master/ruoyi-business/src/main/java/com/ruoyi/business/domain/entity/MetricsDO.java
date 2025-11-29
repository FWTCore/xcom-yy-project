package com.ruoyi.business.domain.entity;

import com.ruoyi.common.core.domain.BaseEntityDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 指标对象 metrics
 *
 * @author xcom
 * @date 2025/11/28
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MetricsDO extends BaseEntityDO {

    /** 系统公司id */
    private Long   deptId;

    /** 项目id */
    private Long   projectId;

    /** 业务id 数字 */
    private Long   bizId;

    /** 业务key 字符串 */
    private String bizKey;

    /** 业务名称 */
    private String metricsName;

    /** 业务类型 */
    private Long   metricsType;

    /** 业务类型 */
    private Long   bizType;

    /** 总数量 */
    private Long   totalCount;

    /** 核对数量 */
    private Long   checkCount;

}
