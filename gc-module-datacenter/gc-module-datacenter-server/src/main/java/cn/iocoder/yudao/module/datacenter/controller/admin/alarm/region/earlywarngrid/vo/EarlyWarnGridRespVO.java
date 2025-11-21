package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarngrid.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 按网格分域预警告警统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EarlyWarnGridRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "统计ID")
    @ExcelProperty("统计ID")
    private String warnGridStatId;

    @Schema(description = "统计周期")
    @ExcelProperty("统计周期")
    private String statCycle;

    @Schema(description = "网格类型")
    @ExcelProperty("网格类型")
    private String gridType;

    @Schema(description = "网格ID")
    @ExcelProperty("网格ID")
    private String gridId;

    @Schema(description = "网格编码")
    @ExcelProperty("网格编码")
    private String gridCode;

    @Schema(description = "网格名称")
    @ExcelProperty("网格名称")
    private String gridName;

    @Schema(description = "所属街道代码")
    @ExcelProperty("所属街道代码")
    private String streetCode;

    @Schema(description = "所属街道名称")
    @ExcelProperty("所属街道名称")
    private String streetName;

    @Schema(description = "预警总数")
    @ExcelProperty("预警总数")
    private Integer totalWarnCount;

    @Schema(description = "已办结数")
    @ExcelProperty("已办结数")
    private Integer completedCount;

    @Schema(description = "待处置数")
    @ExcelProperty("待处置数")
    private Integer pendCount;

    @Schema(description = "主要预警类型")
    @ExcelProperty("主要预警类型")
    private String mainWarnType;

    @Schema(description = "处置率")
    @ExcelProperty("处置率")
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