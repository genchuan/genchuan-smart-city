package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalreview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 复核办理新增/修改 Request VO")
@Data
public class EvalReviewSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "复核ID")
    private String reviewId;

    @Schema(description = "关联申诉ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联申诉ID不能为空")
    private String appealId;

    @Schema(description = "申诉编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "申诉编号不能为空")
    private String appealCode;

    @Schema(description = "关联任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联任务ID不能为空")
    private String evalTaskId;

    @Schema(description = "评价对象ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评价对象ID不能为空")
    private String evalObjectId;

    @Schema(description = "评价对象名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评价对象名称不能为空")
    private String evalObjectName;

    @Schema(description = "复核人员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "复核人员ID不能为空")
    private String reviewUserId;

    @Schema(description = "复核人员姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "复核人员姓名不能为空")
    private String reviewUserName;

    @Schema(description = "复核意见", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "复核意见不能为空")
    private String reviewOpinion;

    @Schema(description = "核查材料")
    private String reviewMats;

    @Schema(description = "复核状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "复核状态不能为空")
    private String reviewStatus;

    @Schema(description = "分配时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "分配时间不能为空")
    private LocalDateTime assignTime;

    @Schema(description = "复核完成时间")
    private LocalDateTime reviewCompleteTime;

    @Schema(description = "分配人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分配人ID不能为空")
    private String assignUserId;

    @Schema(description = "分配人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分配人姓名不能为空")
    private String assignUserName;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}