package com.ruoyi.business.domain.model.response;

import com.ruoyi.business.domain.entity.PaBizOrchestrationDO;
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
public class PaBizOrchestrationVO extends PaBizOrchestrationDO {

    /**
     * PA平台账号
     */
    private String paAccount;

    /**
     * PA平台名称
     */
    private String paUserName;
    /**
     * 智能体id
     */
    private String agentId;
    /**
     * 智能体名称
     */
    private String agentName;
}
