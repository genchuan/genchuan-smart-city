package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.compareanalysisrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 综合对比分析报表 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CompareAnalysisRptRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "报表ID")
    @ExcelProperty("报表ID")
    private String rptId;

    @Schema(description = "对比维度类型")
    @ExcelProperty("对比维度类型")
    private String compareDimType;

    @Schema(description = "统计周期")
    @ExcelProperty("统计周期")
    private String statCycle;

    @Schema(description = "统计周期名称")
    @ExcelProperty("统计周期名称")
    private String statCycleName;

    @Schema(description = "统计指标")
    @ExcelProperty("统计指标")
    private String statIdx;

    @Schema(description = "对比对象1ID")
    @ExcelProperty("对比对象1ID")
    private String compareObj1Id;

    @Schema(description = "对比对象1名称")
    @ExcelProperty("对比对象1名称")
    private String compareObj1Name;

    @Schema(description = "对比对象1数值")
    @ExcelProperty("对比对象1数值")
    private BigDecimal compareObj1Value;

    @Schema(description = "对比对象2ID")
    @ExcelProperty("对比对象2ID")
    private String compareObj2Id;

    @Schema(description = "对比对象2名称")
    @ExcelProperty("对比对象2名称")
    private String compareObj2Name;

    @Schema(description = "对比对象2数值")
    @ExcelProperty("对比对象2数值")
    private BigDecimal compareObj2Value;

    @Schema(description = "平均值")
    @ExcelProperty("平均值")
    private BigDecimal avgValue;

    @Schema(description = "最大值对象ID")
    @ExcelProperty("最大值对象ID")
    private String maxObjId;

    @Schema(description = "最大值对象名称")
    @ExcelProperty("最大值对象名称")
    private String maxObjName;

    @Schema(description = "最大值")
    @ExcelProperty("最大值")
    private BigDecimal maxValue;

    @Schema(description = "最小值对象ID")
    @ExcelProperty("最小值对象ID")
    private String minObjId;

    @Schema(description = "最小值对象名称")
    @ExcelProperty("最小值对象名称")
    private String minObjName;

    @Schema(description = "最小值")
    @ExcelProperty("最小值")
    private BigDecimal minValue;

    @Schema(description = "最大差异值")
    @ExcelProperty("最大差异值")
    private BigDecimal maxDiffValue;

    @Schema(description = "最大差异率")
    @ExcelProperty("最大差异率")
    private BigDecimal maxDiffRate;

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