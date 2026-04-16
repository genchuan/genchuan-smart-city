package cn.iocoder.yudao.module.usermerchant.framework.commom.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FlexibleTimestampDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonToken token = p.getCurrentToken();
        if (token == JsonToken.VALUE_STRING) {
            String str = p.getText().trim();
            // 尝试解析 yyyy-MM-dd HH:mm:ss 格式
            try {
                return LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            } catch (DateTimeParseException e) {
                throw new JsonParseException(p, "无法解析日期时间字符串: " + str);
            }
        } else if (token == JsonToken.VALUE_NUMBER_INT) {
            long timestamp = p.getLongValue();
            // 判断是秒级（10位）还是毫秒级（13位）
            if (timestamp > 9999999999L) {
                return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
            } else {
                return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
            }
        }
        throw new JsonParseException(p, "不支持的 token 类型: " + token);
    }
}