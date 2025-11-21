package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarndevice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 按设备分域预警告警统计新增/修改 Request VO")
@Data
public class EarlyWarnDeviceSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "统计ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计ID不能为空")
    private String warnDeviceStatId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期不能为空")
    private String statCycle;

    @Schema(description = "设备大类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备大类ID不能为空")
    private String deviceMajorId;

    @Schema(description = "设备大类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备大类名称不能为空")
    private String deviceMajorName;

    @Schema(description = "设备型号ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备型号ID不能为空")
    private String deviceModelId;

    @Schema(description = "设备型号名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备型号名称不能为空")
    private String deviceModelName;

    @Schema(description = "预警总数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "预警总数不能为空")
    private Integer totalWarnCount;

    @Schema(description = "主要故障类型")
    private String mainFaultType;

    @Schema(description = "涉及设备数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "涉及设备数不能为空")
    private Integer deviceCount;

    @Schema(description = "在线修复数")
    private Integer onlineRepairCount;

    @Schema(description = "在线修复率")
    private BigDecimal onlineRepairRate;

    @Schema(description = "统计人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计人不能为空")
    private String statUserId;

    @Schema(description = "统计时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计时间不能为空")
    private LocalDateTime statTime;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}