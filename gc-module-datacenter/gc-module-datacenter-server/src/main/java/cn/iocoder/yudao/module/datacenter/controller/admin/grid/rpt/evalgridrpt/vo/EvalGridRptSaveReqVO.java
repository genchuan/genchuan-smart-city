package cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.evalgridrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 评价网格统计新增/修改 Request VO")
@Data
public class EvalGridRptSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "统计ID")
    private String evalStatId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期不能为空")
    private String statCycle;

    @Schema(description = "统计周期名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期名称不能为空")
    private String statCycleName;

    @Schema(description = "行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划代码不能为空")
    private String regionCode;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划名称不能为空")
    private String regionName;

    @Schema(description = "评价网格类型(A/B/C)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评价网格类型(A/B/C)不能为空")
    private String gridType;

    @Schema(description = "关联评价指标ID")
    private String idxId;

    @Schema(description = "关联评价指标名称")
    private String idxName;

    @Schema(description = "网格总数(个)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "网格总数(个)不能为空")
    private Integer totalCount;

    @Schema(description = "总面积(m²)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "总面积(m²)不能为空")
    private Integer totalArea;

    @Schema(description = "平均所含管理数(个)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "平均所含管理数(个)不能为空")
    private BigDecimal avgMgCount;

    @Schema(description = "总所含管理网格数(个)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "总所含管理网格数(个)不能为空")
    private Integer totalMgCount;

    @Schema(description = "类型占比(%)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "类型占比(%)不能为空")
    private BigDecimal typeRatio;

    @Schema(description = "新增网格数(个)")
    private Integer newCount;

    @Schema(description = "统计人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计人不能为空")
    private String statUserId;

    @Schema(description = "统计时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计时间不能为空")
    private LocalDateTime statTime;

    @Schema(description = "报表备注", example = "你对")
    private String rptRemark;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}