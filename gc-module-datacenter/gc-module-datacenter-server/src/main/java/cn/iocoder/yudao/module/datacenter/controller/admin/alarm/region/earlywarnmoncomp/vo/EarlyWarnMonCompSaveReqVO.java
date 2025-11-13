package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmoncomp.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 按监测部件预警告警统计新增/修改 Request VO")
@Data
public class EarlyWarnMonCompSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "统计ID")
    private String warnMonCompStatId;

    @Schema(description = "统计周期")
    private String statCycle;

    @Schema(description = "部件大类ID")
    private String monMajorId;

    @Schema(description = "部件大类名称")
    private String monMajorName;

    @Schema(description = "部件小类ID")
    private String monMinorId;

    @Schema(description = "部件小类名称")
    private String monMinorName;

    @Schema(description = "预警总数")
    private Integer totalWarnCount;

    @Schema(description = "主要触发原因")
    private String mainTriggerReason;

    @Schema(description = "涉及设备数")
    private Integer deviceCount;

    @Schema(description = "已恢复数")
    private Integer recoveredCount;

    @Schema(description = "恢复率")
    private BigDecimal recoveryRate;

    @Schema(description = "统计人")
    private String statUserId;

    @Schema(description = "统计时间")
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