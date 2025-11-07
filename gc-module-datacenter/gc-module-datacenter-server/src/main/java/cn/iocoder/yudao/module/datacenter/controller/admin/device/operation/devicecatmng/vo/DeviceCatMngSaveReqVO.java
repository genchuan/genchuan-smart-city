package cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicecatmng.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备分类管理新增/修改 Request VO")
@Data
public class DeviceCatMngSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "分类管理ID")
    private String catMngId;

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备ID不能为空")
    private String deviceId;

    @Schema(description = "设备名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备名称不能为空")
    private String deviceName;

    @Schema(description = "设备编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备编码不能为空")
    private String deviceCode;

    @Schema(description = "设备大类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备大类ID不能为空")
    private String deviceMajorId;

    @Schema(description = "设备大类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备大类名称不能为空")
    private String deviceMajorName;

    @Schema(description = "设备中类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备中类ID不能为空")
    private String deviceMidId;

    @Schema(description = "设备中类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备中类名称不能为空")
    private String deviceMidName;

    @Schema(description = "设备小类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备小类ID不能为空")
    private String deviceMinorId;

    @Schema(description = "设备小类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备小类名称不能为空")
    private String deviceMinorName;

    @Schema(description = "分类时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "分类时间不能为空")
    private LocalDateTime catTime;

    @Schema(description = "分类人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分类人不能为空")
    private String catUser;

    @Schema(description = "分类状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分类状态不能为空")
    private String catStatus;

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