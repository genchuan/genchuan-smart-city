package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarntime.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 预警告警时间维度统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EarlyWarnTimeRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "统计ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计ID")
    private String timeStatId;

    @Schema(description = "统计维度", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计维度")
    private String timeDimension;

    @Schema(description = "统计周期值", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期值")
    private String statCycleValue;

    @Schema(description = "统计周期名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期名称")
    private String statCycleName;

    @Schema(description = "预警总数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预警总数")
    private Integer totalWarnCount;

    @Schema(description = "主要预警类型")
    @ExcelProperty("主要预警类型")
    private String mainWarnType;

    @Schema(description = "主要预警类型数量")
    @ExcelProperty("主要预警类型数量")
    private Integer mainTypeCount;

    @Schema(description = "已办结数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("已办结数")
    private Integer completedCount;

    @Schema(description = "处置率", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("处置率")
    private BigDecimal handleRate;

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

    @Schema(description = "create_time")
    @ExcelProperty("create_time")
    private LocalDateTime createTime;

}