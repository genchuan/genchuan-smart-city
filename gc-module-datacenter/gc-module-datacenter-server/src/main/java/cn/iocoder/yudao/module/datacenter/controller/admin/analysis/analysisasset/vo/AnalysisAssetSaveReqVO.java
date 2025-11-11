package cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisasset.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 按资产分域分析研判统计新增/修改 Request VO")
@Data
public class AnalysisAssetSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16662")
    private Long id;

    @Schema(description = "统计ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "31130")
    @NotEmpty(message = "统计ID，唯一编码，UUID生成不能为空")
    private String statAnalysisId;

    @Schema(description = "统计周期，格式：年/季/月，如YYYY/YYYYQn/YYYYMM", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期，格式：年/季/月，如YYYY/YYYYQn/YYYYMM不能为空")
    private String statCycle;

    @Schema(description = "统计周期名称，如“2025年Q3”", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "统计周期名称，如“2025年Q3”不能为空")
    private String statCycleName;

    @Schema(description = "资产大类ID，关联资产分类表", requiredMode = Schema.RequiredMode.REQUIRED, example = "7862")
    @NotEmpty(message = "资产大类ID，关联资产分类表不能为空")
    private String assetMajorId;

    @Schema(description = "资产大类名称，与大类ID同步，关联资产分类表", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "资产大类名称，与大类ID同步，关联资产分类表不能为空")
    private String assetMajorName;

    @Schema(description = "资产小类ID，关联资产分类表", example = "13424")
    private String assetMinorId;

    @Schema(description = "资产小类名称，与小类ID同步，关联资产分类表", example = "李四")
    private String assetMinorName;

    @Schema(description = "资产总存量，周期内该分类资产总数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "6586")
    @NotNull(message = "资产总存量，周期内该分类资产总数量不能为空")
    private Integer totalAssetCount;

    @Schema(description = "资产完好率，完好资产数/总存量×100，0.00-100.00", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "资产完好率，完好资产数/总存量×100，0.00-100.00不能为空")
    private BigDecimal assetIntactRate;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

    @Schema(description = "分类扩展字段1，预留")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留")
    private String extCommon2;

}