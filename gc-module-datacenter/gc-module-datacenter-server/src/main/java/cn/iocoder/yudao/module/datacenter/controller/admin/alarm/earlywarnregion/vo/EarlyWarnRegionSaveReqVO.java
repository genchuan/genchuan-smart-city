package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.earlywarnregion.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 按行政区划预警告警统计新增/修改 Request VO")
@Data
public class EarlyWarnRegionSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "统计ID")
    private String warnRegionStatId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期不能为空")
    private String statCycle;

    @Schema(description = "行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划代码不能为空")
    private String regionCode;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划名称不能为空")
    private String regionName;

    @Schema(description = "行政区划级别", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划级别不能为空")
    private String regionLevel;

    @Schema(description = "预警总数")
    private Integer totalWarnCount;

    @Schema(description = "一般预警数")
    private Integer normalLevelCount;

    @Schema(description = "较重预警数")
    private Integer heavyLevelCount;

    @Schema(description = "严重预警数")
    private Integer seriousLevelCount;

    @Schema(description = "特别严重预警数")
    private Integer criticalLevelCount;

    @Schema(description = "已办结数")
    private Integer completedCount;

    @Schema(description = "待处置数")
    private Integer pendCount;

    @Schema(description = "处置率(%)")
    private BigDecimal handleRate;

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