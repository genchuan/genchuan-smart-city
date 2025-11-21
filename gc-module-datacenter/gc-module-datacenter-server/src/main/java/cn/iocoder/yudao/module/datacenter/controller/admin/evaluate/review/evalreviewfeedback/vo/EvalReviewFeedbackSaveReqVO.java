package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalreviewfeedback.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 复核结果反馈新增/修改 Request VO")
@Data
public class EvalReviewFeedbackSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "反馈ID")
    private String reviewFeedbackId;

    @Schema(description = "关联复核ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联复核ID不能为空")
    private String reviewId;

    @Schema(description = "关联申诉ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联申诉ID不能为空")
    private String appealId;

    @Schema(description = "申诉编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "申诉编号不能为空")
    private String appealCode;

    @Schema(description = "评价对象ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评价对象ID不能为空")
    private String evalObjectId;

    @Schema(description = "评价对象名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评价对象名称不能为空")
    private String evalObjectName;

    @Schema(description = "原评价等级", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "原评价等级不能为空")
    private String originalEvalLevel;

    @Schema(description = "原评价得分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "原评价得分不能为空")
    private BigDecimal originalEvalScore;

    @Schema(description = "最终复核结果", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "最终复核结果不能为空")
    private String finalReviewResult;

    @Schema(description = "修正后等级")
    private String revisedLevel;

    @Schema(description = "修正后得分")
    private BigDecimal revisedScore;

    @Schema(description = "反馈意见", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "反馈意见不能为空")
    private String feedbackOpinion;

    @Schema(description = "反馈时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "反馈时间不能为空")
    private LocalDateTime feedbackTime;

    @Schema(description = "反馈方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "反馈方式不能为空")
    private String feedbackMethod;

    @Schema(description = "创建人(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人(业务)不能为空")
    private String createUserBiz;

    @Schema(description = "创建时间(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间(业务)不能为空")
    private LocalDateTime createTimeBiz;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}