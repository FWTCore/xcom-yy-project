package com.ruoyi.business.domain.entity;

import com.ruoyi.common.core.domain.BaseEntityDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * PA平台业务编排;PA平台业务编排对象 pa_biz_orchestration
 *
 * @author xcom
 * @date 2026/1/3
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PaBizOrchestrationDO extends BaseEntityDO {
    /**
     * PA平台账号id
     */
    private Long paAccountId;

    /**
     * PA平台智能体id
     */
    private Long paAgentId;

    /**
     * 业务名称
     */
    private String bizName;

    /**
     * 业务描述
     */
    private String bizDescription;

    /**
     * 业务url地址
     */
    private String bizUrl;

    /**
     * 业务状态
     */
    private Integer biaStatus;
    /**
     * 备注,;
     */
    private String remark;
}
