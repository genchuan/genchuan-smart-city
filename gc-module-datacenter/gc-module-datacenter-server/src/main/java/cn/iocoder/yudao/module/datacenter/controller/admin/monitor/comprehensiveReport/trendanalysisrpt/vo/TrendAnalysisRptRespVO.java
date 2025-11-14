package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.trendanalysisrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 综合趋势分析报表 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TrendAnalysisRptRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "报表ID")
    @ExcelProperty("报表ID")
    private String rptId;

    @Schema(description = "分析维度类型")
    @ExcelProperty("分析维度类型")
    private String trendDimType;

    @Schema(description = "分析维度ID")
    @ExcelProperty("分析维度ID")
    private String trendDimId;

    @Schema(description = "分析维度名称")
    @ExcelProperty("分析维度名称")
    private String trendDimName;

    @Schema(description = "时间粒度")
    @ExcelProperty("时间粒度")
    private String timeGranularity;

    @Schema(description = "起始时间")
    @ExcelProperty("起始时间")
    private LocalDate startTime;

    @Schema(description = "结束时间")
    @ExcelProperty("结束时间")
    private LocalDate endTime;

    @Schema(description = "周期1标识")
    @ExcelProperty("周期1标识")
    private String cycle1Label;

    @Schema(description = "周期1数值")
    @ExcelProperty("周期1数值")
    private BigDecimal cycle1Value;

    @Schema(description = "周期2标识")
    @ExcelProperty("周期2标识")
    private String cycle2Label;

    @Schema(description = "周期2数值")
    @ExcelProperty("周期2数值")
    private BigDecimal cycle2Value;

    @Schema(description = "整体变化率")
    @ExcelProperty("整体变化率")
    private BigDecimal overallChangeRate;

    @Schema(description = "趋势类型")
    @ExcelProperty("趋势类型")
    private String trendType;

    @Schema(description = "报表生成时间")
    @ExcelProperty("报表生成时间")
    private LocalDateTime rptCreateTime;

    @Schema(description = "报表生成人")
    @ExcelProperty("报表生成人")
    private Long rptCreateUser;

    @Schema(description = "报表备注")
    @ExcelProperty("报表备注")
    private String rptRemark;

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