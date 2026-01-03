package com.ruoyi.business.domain.entity;

import com.ruoyi.common.core.domain.BaseEntityDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * PA平台业务办理步骤文件;PA平台业务办理步骤文件对象 pa_biz_process_steps_file
 *
 * @author xcom
 * @date 2026/1/3
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PaBizProcessStepsFileDO extends BaseEntityDO {
    /**
     * PA平台业务编排id
     */
    private Long paBizOrchestrationId;

    /**
     * PA平台业务办理步骤id
     */
    private Long paBizProcessStepsId;

    /**
     * 解析名称
     */
    private String analysisName;

    /**
     * 解析类型
     */
    private Long analysisType;

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

    /**
     * 文件地址
     */
    private String fileUrl;

    /**
     * 解析结果
     */
    private String analysisResult;

    /**
     * 确认结果
     */
    private String confirmResult;
}
