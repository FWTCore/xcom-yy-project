package com.ruoyi.business.domain.model.response;

import com.ruoyi.business.domain.entity.PaAgentDO;
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
public class PaAgentVO extends PaAgentDO {

    /**
     * PA平台账号
     */
    private String paAccount;

    /**
     * PA平台名称
     */
    private String paUserName;
}
