package com.ruoyi.framework.config;

import com.ruoyi.common.config.serializer.LocalDateTimeToLongConverter;
import com.ruoyi.common.config.serializer.LongToLocalDateTimeConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * MongoConfig
 *
 * @author xcom
 * @date 2025/11/17
 */

@Configuration
public class MongoConfig {
    @Bean
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(
            new ArrayList<>(Arrays.asList(new LocalDateTimeToLongConverter(), new LongToLocalDateTimeConverter())));
    }

}
