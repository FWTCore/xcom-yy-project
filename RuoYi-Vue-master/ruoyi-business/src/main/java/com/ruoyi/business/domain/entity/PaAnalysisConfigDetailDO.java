package com.ruoyi.business.domain.entity;

import com.ruoyi.common.core.domain.BaseEntityDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * PA平台分析配置详情;PA平台分析配置详情对象 pa_analysis_config_detail
 *
 * @author xcom
 * @date 2026/1/3
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PaAnalysisConfigDetailDO extends BaseEntityDO {
    /**
     * PA平台分析配置Id
     */
    private Long paAnalysisConfigId;

    /**
     * 副名称
     */
    private String subName;

    /**
     * 文件模板
     */
    private String fileTemplate;

    /**
     * 解析格式
     */
    private String analysisFormat;

    /**
     * 解析数量
     */
    private Long analysisNumber;

    /**
     * 解析说明
     */
    private String analysisDescription;

    /**
     * 解析字段Json
     */
    private String analysisField;
}
