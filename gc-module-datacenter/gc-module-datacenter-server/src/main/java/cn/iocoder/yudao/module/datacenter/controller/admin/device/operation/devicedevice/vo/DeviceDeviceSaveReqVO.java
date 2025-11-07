package cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicedevice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备关联设备新增/修改 Request VO")
@Data
public class DeviceDeviceSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "关联ID")
    private String deviceRelDeviceId;

    @Schema(description = "主设备ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "主设备ID不能为空")
    private String mainDeviceId;

    @Schema(description = "主设备名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "主设备名称不能为空")
    private String mainDeviceName;

    @Schema(description = "主设备编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "主设备编码不能为空")
    private String mainDeviceCode;

    @Schema(description = "从设备ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "从设备ID不能为空")
    private String slaveDeviceId;

    @Schema(description = "从设备名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "从设备名称不能为空")
    private String slaveDeviceName;

    @Schema(description = "从设备编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "从设备编码不能为空")
    private String slaveDeviceCode;

    @Schema(description = "关联类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联类型不能为空")
    private String relType;

    @Schema(description = "关联时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "关联时间不能为空")
    private LocalDateTime relTime;

    @Schema(description = "关联人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联人不能为空")
    private String relUser;

    @Schema(description = "关联状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联状态不能为空")
    private String relStatus;

    @Schema(description = "调整时间")
    private LocalDateTime adjustTime;

    @Schema(description = "调整人")
    private String adjustUser;

    @Schema(description = "调整原因")
    private String adjustReason;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}