package com.ruoyi.framework.business.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * 选择项目上下文
 *
 * @author xcom
 * @date 2025/10/2
 */

@Slf4j
public class SelectProjectContextHolder {

    private static final ThreadLocal<SelectProject> ACCESS_CONTEXT = new TransmittableThreadLocal<>();

    /**
     * 绑定选择项目
     *
     * @param project
     */
    public static void bind(SelectProject project) {
        ACCESS_CONTEXT.set(project);
        log.debug("SelectProject context 绑定成功: {}", ACCESS_CONTEXT.get());
    }

    /**
     * 清除绑定对象
     */
    public static void clear() {
        ACCESS_CONTEXT.remove();
    }

    /**
     * 获取当前上下文的选择项目
     *
     * @return 选择项目
     */
    public static Optional<SelectProject> getAccessUser() {
        return Optional.ofNullable(ACCESS_CONTEXT.get());
    }

}
