package com.ruoyi.business.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * LenientLocalDateTime Deserializer
 *
 * @author xcom
 * @date 2025/10/5
 */

public class LenientLocalDateTimeDeserializer extends LocalDateTimeDeserializer {

    public LenientLocalDateTimeDeserializer() {
        super(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        String text = p.getText();
        if (text == null || text.length() == 0)
            return null;

        // 已经是完整格式
        if (text.length() > 10) {
            return LocalDateTime.parse(text, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
        // 只有日期，补时间
        return LocalDate.parse(text).atStartOfDay();
    }
}