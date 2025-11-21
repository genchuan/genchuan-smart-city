package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalreviewfeedback.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 复核结果反馈分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvalReviewFeedbackPageReqVO extends PageParam {

    @Schema(description = "反馈ID")
    private String reviewFeedbackId;

    @Schema(description = "关联复核ID")
    private String reviewId;

    @Schema(description = "关联申诉ID")
    private String appealId;

    @Schema(description = "申诉编号")
    private String appealCode;

    @Schema(description = "评价对象ID")
    private String evalObjectId;

    @Schema(description = "评价对象名称")
    private String evalObjectName;

    @Schema(description = "原评价等级")
    private String originalEvalLevel;

    @Schema(description = "原评价得分")
    private BigDecimal originalEvalScore;

    @Schema(description = "最终复核结果")
    private String finalReviewResult;

    @Schema(description = "修正后等级")
    private String revisedLevel;

    @Schema(description = "修正后得分")
    private BigDecimal revisedScore;

    @Schema(description = "反馈意见")
    private String feedbackOpinion;

    @Schema(description = "反馈时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] feedbackTime;

    @Schema(description = "反馈方式")
    private String feedbackMethod;

    @Schema(description = "创建人(业务)")
    private String createUserBiz;

    @Schema(description = "创建时间(业务)")
    private LocalDateTime createTimeBiz;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}