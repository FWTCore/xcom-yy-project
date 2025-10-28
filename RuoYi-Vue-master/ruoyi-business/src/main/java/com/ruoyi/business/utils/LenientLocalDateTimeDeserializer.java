package com.ruoyi.business.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * LenientLocalDateTime Deserializer
 *
 * @author xcom
 * @date 2025/10/5
 */

public class LenientLocalDateTimeDeserializer extends LocalDateTimeDeserializer {
    // 明确指定日期和时间格式
    private static final DateTimeFormatter DATE_FORMATTER      = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LenientLocalDateTimeDeserializer() {
        super(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        String text = p.getText().trim();
        if (text.isEmpty()) {
            return null;
        }
        try {
            // 尝试解析为日期时间格式
            if (text.contains(":")) {
                return LocalDateTime.parse(text, DATE_TIME_FORMATTER);
            }
            // 尝试解析为纯日期格式
            else if (text.length() == 10) {
                return LocalDate.parse(text, DATE_FORMATTER).atStartOfDay();
            }
            // 尝试ISO格式（带T分隔符）
            else if (text.contains("T")) {
                return LocalDateTime.parse(text, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            }
        } catch (DateTimeParseException e) {
            // 捕获并重新抛出更友好的异常
            throw new JsonParseException(p,
                "Invalid date format: " + text + ". Supported formats: yyyy-MM-dd, yyyy-MM-dd HH:mm:ss, or ISO format");
        }

        // 所有尝试失败
        throw new JsonParseException(p, "Unrecognized date format: " + text);
    }
}