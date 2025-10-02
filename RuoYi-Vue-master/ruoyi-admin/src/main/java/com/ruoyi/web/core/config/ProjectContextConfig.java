package com.ruoyi.web.core.config;

import com.ruoyi.web.core.interceptor.ProjectContextInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**E:\yy\采集\project\xcom-yy-project\RuoYi-Vue3-master\vite
 * 用户上下文处理 config
 *
 * @author xcom
 * @date 2025/10/2
 */

@Configuration
@Order(25)
public class ProjectContextConfig implements WebMvcConfigurer {
    @Resource
    private ProjectContextInterceptor projectContextInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(projectContextInterceptor).addPathPatterns("/**");
    }
}
