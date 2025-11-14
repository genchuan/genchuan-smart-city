package cn.iocoder.yudao.module.datacenter.controller.admin.device.status.deviceofflinealert.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备离线预警新增/修改 Request VO")
@Data
public class DeviceOfflineAlertSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "预警ID")
    private String offlineAlertId;

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备ID不能为空")
    private String deviceId;

    @Schema(description = "设备名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备名称不能为空")
    private String deviceName;

    @Schema(description = "设备编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备编码不能为空")
    private String deviceCode;

    @Schema(description = "离线开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "离线开始时间不能为空")
    private LocalDateTime offlineStartTime;

    @Schema(description = "离线时长(分钟)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "离线时长(分钟)不能为空")
    private Integer offlineEndure;

    @Schema(description = "预警级别", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "预警级别不能为空")
    private String alertLevel;

    @Schema(description = "预警接收人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "预警接收人ID不能为空")
    private String alertUserId;

    @Schema(description = "预警接收人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "预警接收人姓名不能为空")
    private String alertUserName;

    @Schema(description = "预警推送时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "预警推送时间不能为空")
    private LocalDateTime alertPushTime;

    @Schema(description = "预警状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "预警状态不能为空")
    private String alertStatus;

    @Schema(description = "处理人ID")
    private String handleUserId;

    @Schema(description = "处理人姓名")
    private String handleUserName;

    @Schema(description = "处理时间")
    private LocalDateTime handleTime;

    @Schema(description = "处理结果")
    private String handleResult;

    @Schema(description = "所属行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属行政区划代码不能为空")
    private String regionCode;

    @Schema(description = "所属行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属行政区划名称不能为空")
    private String regionName;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}