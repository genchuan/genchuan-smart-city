package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectefficacyrpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 巡查巡检效率统计新增/修改 Request VO")
@Data
public class InspectEfficacyRptSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "统计ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计ID不能为空")
    private String efficacyStatId;

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

    @Schema(description = "参与巡查人员数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "参与巡查人员数不能为空")
    private Integer participantCount;

    @Schema(description = "总巡查次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "总巡查次数不能为空")
    private Integer totalInspectCount;

    @Schema(description = "人均巡查次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "人均巡查次数不能为空")
    private BigDecimal perPersonInspectCount;

    @Schema(description = "平均单次巡查时长", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "平均单次巡查时长不能为空")
    private Integer avgSingleEndure;

    @Schema(description = "上报问题总数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "上报问题总数不能为空")
    private Integer totalProblemCount;

    @Schema(description = "问题平均整改时长", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "问题平均整改时长不能为空")
    private Integer avgRectifyEndure;

    @Schema(description = "效率评估", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "效率评估不能为空")
    private String efficacyEval;

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