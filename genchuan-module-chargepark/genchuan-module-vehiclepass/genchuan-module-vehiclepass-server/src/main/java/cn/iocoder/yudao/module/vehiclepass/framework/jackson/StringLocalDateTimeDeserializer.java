package cn.iocoder.yudao.module.vehiclepass.framework.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * 字符串格式的 LocalDateTime 反序列化器。
 * 本类作字段级覆盖:优先按 "yyyy-MM-dd HH:mm:ss" 解析,解析不过再 fallback 到时间戳(兼容前端两种写法)。
 */
public class StringLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter FMT_ISO = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String text = p.getValueAsString();
        if (text == null || text.isEmpty()) {
            return null;
        }
        // 纯数字串 = 时间戳
        if (text.matches("^\\d{10,}$")) {
            try {
                long ms = Long.parseLong(text);
                return LocalDateTime.ofInstant(Instant.ofEpochMilli(ms), ZoneId.systemDefault());
            } catch (NumberFormatException ignore) {
                // 继续走字符串解析
            }
        }
        // 标准 "yyyy-MM-dd HH:mm:ss"
        try {
            return LocalDateTime.parse(text, FMT);
        } catch (DateTimeParseException ignore) {
            // ISO 格式(带 T)兜底
            return LocalDateTime.parse(text, FMT_ISO);
        }
    }

}
