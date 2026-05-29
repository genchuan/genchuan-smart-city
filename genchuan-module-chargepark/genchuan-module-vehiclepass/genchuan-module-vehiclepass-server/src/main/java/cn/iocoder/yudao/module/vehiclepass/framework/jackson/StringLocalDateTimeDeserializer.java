//package cn.iocoder.yudao.module.vehiclepass.framework.jackson;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//
///**
// * 字符串格式的 LocalDateTime 反序列化器。
// * 本类作字段级覆盖:优先按 "yyyy-MM-dd HH:mm:ss" 解析,解析不过再 fallback 到时间戳(兼容前端两种写法)。
// */
//public class StringLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
//
//    @Override
//    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
//        return LocalDateTimeParseUtils.parse(p.getValueAsString());
//    }
//
//}
