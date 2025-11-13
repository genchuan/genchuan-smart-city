package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.relatedriskident.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 关联风险识别新增/修改 Request VO")
@Data
public class RelatedRiskIdentSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "识别ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "识别ID不能为空")
    private String identId;

    @Schema(description = "触发预警ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "触发预警ID不能为空")
    private String triggerWarnId;

    @Schema(description = "触发预警名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "触发预警名称不能为空")
    private String triggerWarnName;

    @Schema(description = "触发预警类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "触发预警类型不能为空")
    private String triggerWarnType;

    @Schema(description = "关联风险名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联风险名称不能为空")
    private String relatedRiskName;

    @Schema(description = "关联风险类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联风险类型不能为空")
    private String relatedRiskType;

    @Schema(description = "关联依据", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联依据不能为空")
    private String relatedBasis;

    @Schema(description = "风险等级", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "风险等级不能为空")
    private String riskLevel;

    @Schema(description = "识别时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "识别时间不能为空")
    private LocalDateTime identTime;

    @Schema(description = "识别方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "识别方式不能为空")
    private String identMethod;

    @Schema(description = "识别用户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "识别用户ID不能为空")
    private String identifyUserId;

    @Schema(description = "识别用户姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "识别用户姓名不能为空")
    private String identifyUserName;

    @Schema(description = "是否已处置", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "是否已处置不能为空")
    private String isHandled;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}