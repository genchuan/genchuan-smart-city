package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectarearpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 巡查巡检区域统计新增/修改 Request VO")
@Data
public class InspectAreaRptSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "统计ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计ID不能为空")
    private String areaStatId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期不能为空")
    private String statCycle;

    @Schema(description = "统计周期名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期名称不能为空")
    private String statCycleName;

    @Schema(description = "行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划代码不能为空")
    private String areaCode;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划名称不能为空")
    private String areaName;

    @Schema(description = "上级区域名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "上级区域名称不能为空")
    private String parentAreaName;

    @Schema(description = "日常巡查次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "日常巡查次数不能为空")
    private Integer dailyInspectCount;

    @Schema(description = "专项巡查次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "专项巡查次数不能为空")
    private Integer specInspectCount;

    @Schema(description = "应急巡查次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "应急巡查次数不能为空")
    private Integer emerInspectCount;

    @Schema(description = "上报问题总数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "上报问题总数不能为空")
    private Integer totalProblemCount;

    @Schema(description = "整改完成数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "整改完成数不能为空")
    private Integer rectifyCompleteCount;

    @Schema(description = "整改率", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "整改率不能为空")
    private BigDecimal rectifyRate;

    @Schema(description = "问题集中类型")
    private String concentratedProblemType;

    @Schema(description = "统计人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计人不能为空")
    private String statUser;

    @Schema(description = "统计时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计时间不能为空")
    private LocalDateTime statTime;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}