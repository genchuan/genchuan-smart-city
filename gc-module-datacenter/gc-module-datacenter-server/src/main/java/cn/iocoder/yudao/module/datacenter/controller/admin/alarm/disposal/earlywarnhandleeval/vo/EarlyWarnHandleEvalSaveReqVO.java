package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnhandleeval.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 预警告警处置评估新增/修改 Request VO")
@Data
public class EarlyWarnHandleEvalSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "评估ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评估ID不能为空")
    private String evalId;

    @Schema(description = "预警ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "预警ID不能为空")
    private String earlyWarnId;

    @Schema(description = "派单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "派单ID不能为空")
    private String dispatchId;

    @Schema(description = "评估人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评估人ID不能为空")
    private String evalUserId;

    @Schema(description = "评估人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评估人姓名不能为空")
    private String evalUserName;

    @Schema(description = "问题解决度得分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "问题解决度得分不能为空")
    private Integer problemSolveScore;

    @Schema(description = "响应及时性得分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "响应及时性得分不能为空")
    private Integer timelinessScore;

    @Schema(description = "现场规范性得分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "现场规范性得分不能为空")
    private Integer stdizationScore;

    @Schema(description = "综合得分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "综合得分不能为空")
    private BigDecimal comprehensiveScore;

    @Schema(description = "评估等级", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评估等级不能为空")
    private String evalLevel;

    @Schema(description = "评估时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "评估时间不能为空")
    private LocalDateTime evalTime;

    @Schema(description = "评估备注")
    private String evalRemark;

    @Schema(description = "是否二次处置", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "是否二次处置不能为空")
    private String isSecondHandle;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}