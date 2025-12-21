package com.ruoyi.business.event;

import lombok.Getter;
import org.springframework.security.core.context.SecurityContext;

/**
 * 核实资产事件
 *
 * @author xcom
 * @date 2025/12/21
 */

@Getter
public class VerifyAssetEvent extends BaseEvent {

    private final Long projectId;
    /**
     * 1：实物；2：账务
     */
    private final Integer bizType;

    public VerifyAssetEvent(Object source, Long projectId, Integer bizType, SecurityContext securityContext) {
        super(source, securityContext);
        this.projectId = projectId;
        this.bizType = bizType;
    }

}
