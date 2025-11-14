package cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismngcomp.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 按管理部件分析研判统计新增/修改 Request VO")
@Data
public class AnalysisMngCompSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9768")
    private Long id;

    @Schema(description = "统计ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "9853")
    @NotEmpty(message = "统计ID，唯一编码，UUID生成不能为空")
    private String statAnalysisId;

    @Schema(description = "统计周期，格式：年/季/月，如YYYY/YYYYQn/YYYYMM", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期，格式：年/季/月，如YYYY/YYYYQn/YYYYMM不能为空")
    private String statCycle;

    @Schema(description = "统计周期名称，如“2025年Q3”", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "统计周期名称，如“2025年Q3”不能为空")
    private String statCycleName;

    @Schema(description = "部件大类ID，关联管理部件分类表", requiredMode = Schema.RequiredMode.REQUIRED, example = "2595")
    @NotEmpty(message = "部件大类ID，关联管理部件分类表不能为空")
    private String compMajorId;

    @Schema(description = "部件大类名称，与大类ID同步，关联管理部件分类表", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "部件大类名称，与大类ID同步，关联管理部件分类表不能为空")
    private String compMajorName;

    @Schema(description = "部件小类ID，关联管理部件分类表", example = "7409")
    private String compMinorId;

    @Schema(description = "部件小类名称，与小类ID同步，关联管理部件分类表", example = "王五")
    private String compMinorName;

    @Schema(description = "部件总存量，周期内该分类部件总数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "部件总存量，周期内该分类部件总数量不能为空")
    private Integer totalCompStock;

    @Schema(description = "损坏部件数，周期内该分类损坏部件数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "10554")
    @NotNull(message = "损坏部件数，周期内该分类损坏部件数量不能为空")
    private Integer damagedCompCount;

    @Schema(description = "部件损坏率，损坏部件数/总存量×100，0.00-100.00", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "部件损坏率，损坏部件数/总存量×100，0.00-100.00不能为空")
    private BigDecimal compDamageRate;

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