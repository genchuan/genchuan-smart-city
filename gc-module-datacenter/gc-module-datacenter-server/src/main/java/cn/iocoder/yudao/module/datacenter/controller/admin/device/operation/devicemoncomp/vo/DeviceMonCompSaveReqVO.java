package cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicemoncomp.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备关联监测部件新增/修改 Request VO")
@Data
public class DeviceMonCompSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "关联ID")
    private String deviceRelMonCompId;

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备ID不能为空")
    private String deviceId;

    @Schema(description = "设备名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备名称不能为空")
    private String deviceName;

    @Schema(description = "设备编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备编码不能为空")
    private String deviceCode;

    @Schema(description = "监测部件ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "监测部件ID不能为空")
    private String monCompId;

    @Schema(description = "监测部件名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "监测部件名称不能为空")
    private String monCompName;

    @Schema(description = "监测部件编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "监测部件编码不能为空")
    private String monCompCode;

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