package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarndevice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 按设备分域预警告警统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EarlyWarnDeviceRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "统计ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计ID")
    private String warnDeviceStatId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期")
    private String statCycle;

    @Schema(description = "设备大类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备大类ID")
    private String deviceMajorId;

    @Schema(description = "设备大类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备大类名称")
    private String deviceMajorName;

    @Schema(description = "设备型号ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备型号ID")
    private String deviceModelId;

    @Schema(description = "设备型号名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备型号名称")
    private String deviceModelName;

    @Schema(description = "预警总数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预警总数")
    private Integer totalWarnCount;

    @Schema(description = "主要故障类型")
    @ExcelProperty("主要故障类型")
    private String mainFaultType;

    @Schema(description = "涉及设备数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("涉及设备数")
    private Integer deviceCount;

    @Schema(description = "在线修复数")
    @ExcelProperty("在线修复数")
    private Integer onlineRepairCount;

    @Schema(description = "在线修复率")
    @ExcelProperty("在线修复率")
    private BigDecimal onlineRepairRate;

    @Schema(description = "统计人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计人")
    private String statUserId;

    @Schema(description = "统计时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计时间")
    private LocalDateTime statTime;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}