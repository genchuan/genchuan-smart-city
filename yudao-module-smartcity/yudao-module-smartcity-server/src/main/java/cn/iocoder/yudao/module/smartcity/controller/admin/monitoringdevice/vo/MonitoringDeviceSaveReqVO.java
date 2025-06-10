package cn.iocoder.yudao.module.smartcity.controller.admin.monitoringdevice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 监测设备新增/修改 Request VO")
@Data
public class MonitoringDeviceSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "26353")
    private Long id;

    @Schema(description = "设备编号")
    private String deviceNumber;

    @Schema(description = "设备名称", example = "李四")
    private String deviceName;

    @Schema(description = "设备类型", example = "2")
    private String deviceType;

    @Schema(description = "生产厂家")
    private String manufacturer;

    @Schema(description = "维护记录")
    private String maintenanceRecord;

    @Schema(description = "安装时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "安装时间不能为空")
    private LocalDateTime createTime;

}