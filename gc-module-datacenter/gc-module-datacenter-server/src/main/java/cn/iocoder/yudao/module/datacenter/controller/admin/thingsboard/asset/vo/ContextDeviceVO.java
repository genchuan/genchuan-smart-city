package cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Schema(description = "关联设备VO")
@Data
public class ContextDeviceVO {

    @Schema(description = "设备名称")
    private String deviceName;

    @Schema(description = "实体类型")
    private String entityType;

    @Schema(description = "设备ID")
    private String deviceId;

}
