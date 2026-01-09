package cn.iocoder.yudao.module.datacenter.service.thingsboard.device.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public class DeviceProfileIdDeserializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        // 如果前端传的是对象 {entityType: "...", id: "..."}，提取id字段
        if (p.getCurrentToken() == JsonToken.START_OBJECT) {
            String id = null;
            while (p.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = p.getCurrentName();
                p.nextToken();
                if ("id".equals(fieldName)) {
                    id = p.getValueAsString();
                }
            }
            return id;
        }
        // 如果前端传的是字符串，直接返回
        else if (p.getCurrentToken() == JsonToken.VALUE_STRING) {
            return p.getValueAsString();
        }
        return null;
    }
}