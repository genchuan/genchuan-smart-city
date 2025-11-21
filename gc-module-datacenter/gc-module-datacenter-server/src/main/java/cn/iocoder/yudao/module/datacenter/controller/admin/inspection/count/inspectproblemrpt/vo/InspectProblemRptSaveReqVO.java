package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectproblemrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 巡查巡检问题统计新增/修改 Request VO")
@Data
public class InspectProblemRptSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "统计ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计ID不能为空")
    private String problemStatId;

    @Schema(description = "统计周期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期不能为空")
    private String statCycle;

    @Schema(description = "统计周期名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期名称不能为空")
    private String statCycleName;

    @Schema(description = "所属区域代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属区域代码不能为空")
    private String areaCode;

    @Schema(description = "所属区域名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属区域名称不能为空")
    private String areaName;

    @Schema(description = "问题类型ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "问题类型ID不能为空")
    private String problemTypeId;

    @Schema(description = "问题类型名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "问题类型名称不能为空")
    private String problemTypeName;

    @Schema(description = "上报问题数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "上报问题数不能为空")
    private Integer rptProblemCount;

    @Schema(description = "一级问题数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "一级问题数不能为空")
    private Integer level1ProblemCount;

    @Schema(description = "整改完成数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "整改完成数不能为空")
    private Integer rectifyCompleteCount;

    @Schema(description = "整改率", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "整改率不能为空")
    private BigDecimal rectifyRate;

    @Schema(description = "未整改原因")
    private String unrectifiedReason;

    @Schema(description = "问题集中区域")
    private String concentratedArea;

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