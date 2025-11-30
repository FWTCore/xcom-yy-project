package com.ruoyi.business.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.springframework.security.core.context.SecurityContext;

/**
 * BaseEvent
 *
 * @author xcom
 * @date 2025/11/30
 */

@Getter
public class BaseEvent extends ApplicationEvent {

    private final SecurityContext securityContext;

    public BaseEvent(Object source, SecurityContext securityContext) {
        super(source);
        this.securityContext = securityContext;
    }
}
