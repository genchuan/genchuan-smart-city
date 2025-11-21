package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalreviewfeedback.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 复核结果反馈 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvalReviewFeedbackRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "反馈ID")
    @ExcelProperty("反馈ID")
    private String reviewFeedbackId;

    @Schema(description = "关联复核ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联复核ID")
    private String reviewId;

    @Schema(description = "关联申诉ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联申诉ID")
    private String appealId;

    @Schema(description = "申诉编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("申诉编号")
    private String appealCode;

    @Schema(description = "评价对象ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评价对象ID")
    private String evalObjectId;

    @Schema(description = "评价对象名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评价对象名称")
    private String evalObjectName;

    @Schema(description = "原评价等级", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("原评价等级")
    private String originalEvalLevel;

    @Schema(description = "原评价得分", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("原评价得分")
    private BigDecimal originalEvalScore;

    @Schema(description = "最终复核结果", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("最终复核结果")
    private String finalReviewResult;

    @Schema(description = "修正后等级")
    @ExcelProperty("修正后等级")
    private String revisedLevel;

    @Schema(description = "修正后得分")
    @ExcelProperty("修正后得分")
    private BigDecimal revisedScore;

    @Schema(description = "反馈意见", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("反馈意见")
    private String feedbackOpinion;

    @Schema(description = "反馈时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("反馈时间")
    private LocalDateTime feedbackTime;

    @Schema(description = "反馈方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("反馈方式")
    private String feedbackMethod;

    @Schema(description = "创建人(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人(业务)")
    private String createUserBiz;

    @Schema(description = "创建时间(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间(业务)")
    private LocalDateTime createTimeBiz;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}