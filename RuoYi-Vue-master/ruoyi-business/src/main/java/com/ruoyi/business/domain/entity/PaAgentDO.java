package com.ruoyi.business.domain.entity;

import com.ruoyi.common.core.domain.BaseEntityDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * PA平台智能体;PA平台智能体对象 pa_agent
 *
 * @author xcom
 * @date 2026/1/2
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PaAgentDO extends BaseEntityDO {

    /**
     * PA平台账号id
     */
    private Long paAccountId;

    /**
     * 智能体id
     */
    private String agentId;

    /**
     * 智能体名称
     */
    private String agentName;

    /**
     * 智能体描述
     */
    private String agentDescription;

    /**
     * 智能体状态
     */
    private Integer agentStatus;
}
