package cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismngmatter.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 按管理事项分析研判统计新增/修改 Request VO")
@Data
public class AnalysisMngMatterSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14266")
    private Long id;

    @Schema(description = "统计ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "31916")
    @NotEmpty(message = "统计ID，唯一编码，UUID生成不能为空")
    private String statAnalysisId;

    @Schema(description = "统计周期，格式：年/季/月，如YYYY/YYYYQn/YYYYMM", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期，格式：年/季/月，如YYYY/YYYYQn/YYYYMM不能为空")
    private String statCycle;

    @Schema(description = "统计周期名称，如“2025年09月”", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "统计周期名称，如“2025年09月”不能为空")
    private String statCycleName;

    @Schema(description = "事项大类ID，关联管理事项分类表", requiredMode = Schema.RequiredMode.REQUIRED, example = "17953")
    @NotEmpty(message = "事项大类ID，关联管理事项分类表不能为空")
    private String matterMajorId;

    @Schema(description = "事项大类名称，与大类ID同步，关联管理事项分类表", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "事项大类名称，与大类ID同步，关联管理事项分类表不能为空")
    private String matterMajorName;

    @Schema(description = "事项小类ID，关联管理事项分类表", example = "25349")
    private String matterMinorId;

    @Schema(description = "事项小类名称，与小类ID同步，关联管理事项分类表", example = "张三")
    private String matterMinorName;

    @Schema(description = "处置部门代码，关联部门信息表")
    private String deptCode;

    @Schema(description = "处置部门名称，与部门代码同步，关联部门信息表", example = "王五")
    private String deptName;

    @Schema(description = "事项上报总量，周期内该分类事项上报总数", requiredMode = Schema.RequiredMode.REQUIRED, example = "9112")
    @NotNull(message = "事项上报总量，周期内该分类事项上报总数不能为空")
    private Integer totalRptCount;

    @Schema(description = "事项办结率，办结事项数/(上报总量-驳回数)×100，0.00-100.00", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "事项办结率，办结事项数/(上报总量-驳回数)×100，0.00-100.00不能为空")
    private BigDecimal matterCompleteRate;

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