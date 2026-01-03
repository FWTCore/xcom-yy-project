package com.ruoyi.business.domain.entity;

import com.ruoyi.common.core.domain.BaseEntityDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * PA平台分析配置;PA平台分析配置对象 pa_analysis_config
 *
 * @author xcom
 * @date 2026/1/2
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PaAnalysisConfigDO extends BaseEntityDO {
    /**
     * 解析名称
     */
    private String analysisName;

    /**
     * 解析类型
     */
    private Long analysisType;

    /**
     * 配置状态
     */
    private Integer configStatus;

    /**
     * 备注,;
     */
    private String remark;

}
