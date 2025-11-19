package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspecttyperpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 巡查巡检类型统计新增/修改 Request VO")
@Data
public class InspectTypeRptSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "统计ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计ID不能为空")
    private String typeStatId;

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

    @Schema(description = "巡查类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "巡查类型不能为空")
    private String inspectType;

    @Schema(description = "巡查次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "巡查次数不能为空")
    private Integer inspectCount;

    @Schema(description = "参与人员数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "参与人员数不能为空")
    private Integer participantCount;

    @Schema(description = "发现问题数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "发现问题数不能为空")
    private Integer foundProblemCount;

    @Schema(description = "问题发现率", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "问题发现率不能为空")
    private BigDecimal problemFoundRate;

    @Schema(description = "整改完成数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "整改完成数不能为空")
    private Integer rectifyCompleteCount;

    @Schema(description = "整改率", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "整改率不能为空")
    private BigDecimal rectifyRate;

    @Schema(description = "平均巡查时长")
    private Integer avgInspectEndure;

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