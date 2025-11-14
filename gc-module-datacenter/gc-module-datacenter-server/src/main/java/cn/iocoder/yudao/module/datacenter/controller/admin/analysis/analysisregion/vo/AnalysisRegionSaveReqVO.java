package cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisregion.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 按行政区划分析研判统计新增/修改 Request VO")
@Data
public class AnalysisRegionSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20868")
    private Long id;

    @Schema(description = "统计ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "18816")
    @NotEmpty(message = "统计ID，唯一编码，UUID生成不能为空")
    private String statAnalysisId;

    @Schema(description = "统计周期，格式：年/季/月，如YYYY/YYYYQn/YYYYMM", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期，格式：年/季/月，如YYYY/YYYYQn/YYYYMM不能为空")
    private String statCycle;

    @Schema(description = "统计周期名称，中文描述，如“2025年Q3”", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "统计周期名称，中文描述，如“2025年Q3”不能为空")
    private String statCycleName;

    @Schema(description = "行政区划代码，符合GB/T 2260，关联行政区划表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划代码，符合GB/T 2260，关联行政区划表不能为空")
    private String regionCode;

    @Schema(description = "行政区划名称，与代码同步，关联行政区划表", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "行政区划名称，与代码同步，关联行政区划表不能为空")
    private String regionName;

    @Schema(description = "行政区划级别，标识行政层级，如省级/市级/县级/街道级/社区级", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划级别，标识行政层级，如省级/市级/县级/街道级/社区级不能为空")
    private String regionLevel;

    @Schema(description = "正常监测部件数，周期内该区域正常监测部件数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "2073")
    @NotNull(message = "正常监测部件数，周期内该区域正常监测部件数量不能为空")
    private Integer normalMonCompCount;

    @Schema(description = "异常监测部件数，周期内该区域异常监测部件数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "12707")
    @NotNull(message = "异常监测部件数，周期内该区域异常监测部件数量不能为空")
    private Integer abnMonCompCount;

    @Schema(description = "监测事件办结率，周期内该区域监测事件办结率，0.00-100.00", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "监测事件办结率，周期内该区域监测事件办结率，0.00-100.00不能为空")
    private BigDecimal monEvtCompleteRate;

    @Schema(description = "统计人，生成报表的用户ID，关联用户信息表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计人，生成报表的用户ID，关联用户信息表不能为空")
    private String statUser;

    @Schema(description = "统计时间，格式：yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计时间，格式：yyyy-MM-dd HH:mm:ss不能为空")
    private LocalDateTime statTime;

    @Schema(description = "报表备注，如“统计范围:该区域建成区”", example = "随便")
    private String rptRemark;

    @Schema(description = "分类扩展字段1，预留，如“统计范围”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“统计范围”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储额外统计维度")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储额外统计维度")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}