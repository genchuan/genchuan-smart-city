package cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.thingsboard.server.common.data.kv.DataType;

import java.util.Map;

@Schema(description = "管理后台 - 设备属性 Response VO")
@Data
public class DeviceAttributeRespVO {

    @Schema(description = "属性键")
    private String key;

    @Schema(description = "属性值")
    private Object value;

    @Schema(description = "数据类型")
    private DataType dataType;

    @Schema(description = "最后更新时间")
    private Long lastUpdateTs;

    // 可以根据需要保留类型特定的字段，或者只保留通用的 value 字段
    @Schema(description = "字符串形式的值")
    private String valueAsString;

    // 添加获取字符串值的方法
    public String getValueAsString() {
        if (valueAsString != null) {
            return valueAsString;
        }
        return value != null ? value.toString() : null;
    }
}