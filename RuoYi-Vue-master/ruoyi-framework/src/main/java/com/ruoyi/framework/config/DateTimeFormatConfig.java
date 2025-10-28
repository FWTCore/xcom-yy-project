package com.ruoyi.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.format.DateTimeFormatter;

/**
 * 配置 LocalDate 和 LocalDateTime 的全局格式
 *
 * @author xcom
 * @date 2025/10/28
 */
@Configuration
public class DateTimeFormatConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(org.springframework.format.FormatterRegistry registry) {
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setDateFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        registrar.registerFormatters(registry);
    }
}