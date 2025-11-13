package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnregion.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 按行政区划预警告警统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EarlyWarnRegionRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "统计ID")
    @ExcelProperty("统计ID")
    private String warnRegionStatId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期")
    private String statCycle;

    @Schema(description = "行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划代码")
    private String regionCode;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划名称")
    private String regionName;

    @Schema(description = "行政区划级别", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划级别")
    private String regionLevel;

    @Schema(description = "预警总数")
    @ExcelProperty("预警总数")
    private Integer totalWarnCount;

    @Schema(description = "一般预警数")
    @ExcelProperty("一般预警数")
    private Integer normalLevelCount;

    @Schema(description = "较重预警数")
    @ExcelProperty("较重预警数")
    private Integer heavyLevelCount;

    @Schema(description = "严重预警数")
    @ExcelProperty("严重预警数")
    private Integer seriousLevelCount;

    @Schema(description = "特别严重预警数")
    @ExcelProperty("特别严重预警数")
    private Integer criticalLevelCount;

    @Schema(description = "已办结数")
    @ExcelProperty("已办结数")
    private Integer completedCount;

    @Schema(description = "待处置数")
    @ExcelProperty("待处置数")
    private Integer pendCount;

    @Schema(description = "处置率(%)")
    @ExcelProperty("处置率(%)")
    private BigDecimal handleRate;

    @Schema(description = "统计人")
    @ExcelProperty("统计人")
    private String statUserId;

    @Schema(description = "统计时间")
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