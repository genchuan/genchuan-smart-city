package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnarea.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 预警告警区域维度统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EarlyWarnAreaRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "统计ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计ID")
    private String regionStatId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期")
    private String statCycle;

    @Schema(description = "行政区划级别", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划级别")
    private String adminLevel;

    @Schema(description = "行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划代码")
    private String adminCode;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划名称")
    private String adminName;

    @Schema(description = "预警总数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预警总数")
    private Integer totalWarnCount;

    @Schema(description = "严重等级数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("严重等级数")
    private Integer criticalCount;

    @Schema(description = "已办结数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("已办结数")
    private Integer completedCount;

    @Schema(description = "处置率", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("处置率")
    private BigDecimal handleRate;

    @Schema(description = "主要预警类型")
    @ExcelProperty("主要预警类型")
    private String mainWarnType;

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