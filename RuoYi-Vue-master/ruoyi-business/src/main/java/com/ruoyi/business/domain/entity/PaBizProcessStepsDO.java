package com.ruoyi.business.domain.entity;

import com.ruoyi.common.core.domain.BaseEntityDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * PA平台业务办理步骤;PA平台业务办理步骤对象 pa_biz_process_steps
 *
 * @author xcom
 * @date 2026/1/3
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PaBizProcessStepsDO extends BaseEntityDO {

    /**
     * PA平台业务编排id
     */
    private Long paBizOrchestrationId;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 步骤名称
     */
    private String stepsName;

    /**
     * 步骤描述
     */
    private String stepsDescription;

    /**
     * 步骤属性
     */
    private String stepsAttribute;
}
