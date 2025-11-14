package cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisasset.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;

import java.time.LocalDateTime;


@Schema(description = "管理后台 - 按资产分域分析研判统计分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AnalysisAssetPageReqVO extends PageParam {

    @Schema(description = "统计ID，唯一编码，UUID生成", example = "31130")
    private String statAnalysisId;

    @Schema(description = "统计周期，格式：年/季/月，如YYYY/YYYYQn/YYYYMM")
    private String statCycle;

    @Schema(description = "统计周期名称，如“2025年Q3”", example = "芋艿")
    private String statCycleName;

    @Schema(description = "资产大类ID，关联资产分类表", example = "7862")
    private String assetMajorId;

    @Schema(description = "资产大类名称，与大类ID同步，关联资产分类表", example = "王五")
    private String assetMajorName;

    @Schema(description = "资产小类ID，关联资产分类表", example = "13424")
    private String assetMinorId;

    @Schema(description = "资产小类名称，与小类ID同步，关联资产分类表", example = "李四")
    private String assetMinorName;

    @Schema(description = "资产总存量，周期内该分类资产总数量", example = "6586")
    private Integer totalAssetCount;

    @Schema(description = "资产完好率，完好资产数/总存量×100，0.00-100.00")
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