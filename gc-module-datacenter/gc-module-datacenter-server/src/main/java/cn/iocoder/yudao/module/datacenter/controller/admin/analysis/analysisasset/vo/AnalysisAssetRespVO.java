package cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisasset.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 按资产分域分析研判统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AnalysisAssetRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16662")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "统计ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "31130")
    @ExcelProperty("统计ID，唯一编码，UUID生成")
    private String statAnalysisId;

    @Schema(description = "统计周期，格式：年/季/月，如YYYY/YYYYQn/YYYYMM", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期，格式：年/季/月，如YYYY/YYYYQn/YYYYMM")
    private String statCycle;

    @Schema(description = "统计周期名称，如“2025年Q3”", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("统计周期名称，如“2025年Q3”")
    private String statCycleName;

    @Schema(description = "资产大类ID，关联资产分类表", requiredMode = Schema.RequiredMode.REQUIRED, example = "7862")
    @ExcelProperty("资产大类ID，关联资产分类表")
    private String assetMajorId;

    @Schema(description = "资产大类名称，与大类ID同步，关联资产分类表", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("资产大类名称，与大类ID同步，关联资产分类表")
    private String assetMajorName;

    @Schema(description = "资产小类ID，关联资产分类表", example = "13424")
    @ExcelProperty("资产小类ID，关联资产分类表")
    private String assetMinorId;

    @Schema(description = "资产小类名称，与小类ID同步，关联资产分类表", example = "李四")
    @ExcelProperty("资产小类名称，与小类ID同步，关联资产分类表")
    private String assetMinorName;

    @Schema(description = "资产总存量，周期内该分类资产总数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "6586")
    @ExcelProperty("资产总存量，周期内该分类资产总数量")
    private Integer totalAssetCount;

    @Schema(description = "资产完好率，完好资产数/总存量×100，0.00-100.00", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("资产完好率，完好资产数/总存量×100，0.00-100.00")
    private BigDecimal assetIntactRate;

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