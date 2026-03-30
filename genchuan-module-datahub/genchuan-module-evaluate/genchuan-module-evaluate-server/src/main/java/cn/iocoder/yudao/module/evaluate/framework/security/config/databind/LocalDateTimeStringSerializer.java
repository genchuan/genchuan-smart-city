package cn.iocoder.yudao.module.evaluate.framework.security.config.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 将 LocalDateTime 序列化为固定格式字符串，避免被全局时间戳序列化器覆盖。
 */
public class LocalDateTimeStringSerializer extends JsonSerializer<LocalDateTime> {

    public static final LocalDateTimeStringSerializer INSTANCE = new LocalDateTimeStringSerializer();

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNull();
            return;
        }
        gen.writeString(value.format(FORMATTER));
    }
}

