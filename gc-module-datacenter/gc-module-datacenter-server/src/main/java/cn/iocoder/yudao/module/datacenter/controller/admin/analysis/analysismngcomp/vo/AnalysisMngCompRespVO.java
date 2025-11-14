package cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismngcomp.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 按管理部件分析研判统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AnalysisMngCompRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9768")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "统计ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "9853")
    @ExcelProperty("统计ID，唯一编码，UUID生成")
    private String statAnalysisId;

    @Schema(description = "统计周期，格式：年/季/月，如YYYY/YYYYQn/YYYYMM", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期，格式：年/季/月，如YYYY/YYYYQn/YYYYMM")
    private String statCycle;

    @Schema(description = "统计周期名称，如“2025年Q3”", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("统计周期名称，如“2025年Q3”")
    private String statCycleName;

    @Schema(description = "部件大类ID，关联管理部件分类表", requiredMode = Schema.RequiredMode.REQUIRED, example = "2595")
    @ExcelProperty("部件大类ID，关联管理部件分类表")
    private String compMajorId;

    @Schema(description = "部件大类名称，与大类ID同步，关联管理部件分类表", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("部件大类名称，与大类ID同步，关联管理部件分类表")
    private String compMajorName;

    @Schema(description = "部件小类ID，关联管理部件分类表", example = "7409")
    @ExcelProperty("部件小类ID，关联管理部件分类表")
    private String compMinorId;

    @Schema(description = "部件小类名称，与小类ID同步，关联管理部件分类表", example = "王五")
    @ExcelProperty("部件小类名称，与小类ID同步，关联管理部件分类表")
    private String compMinorName;

    @Schema(description = "部件总存量，周期内该分类部件总数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("部件总存量，周期内该分类部件总数量")
    private Integer totalCompStock;

    @Schema(description = "损坏部件数，周期内该分类损坏部件数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "10554")
    @ExcelProperty("损坏部件数，周期内该分类损坏部件数量")
    private Integer damagedCompCount;

    @Schema(description = "部件损坏率，损坏部件数/总存量×100，0.00-100.00", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("部件损坏率，损坏部件数/总存量×100，0.00-100.00")
    private BigDecimal compDamageRate;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

    @Schema(description = "分类扩展字段1，预留")
    @ExcelProperty("分类扩展字段1，预留")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留")
    @ExcelProperty("分类扩展字段2，预留")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留")
    @ExcelProperty("通用扩展字段1，预留")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留")
    @ExcelProperty("通用扩展字段2，预留")
    private String extCommon2;

}