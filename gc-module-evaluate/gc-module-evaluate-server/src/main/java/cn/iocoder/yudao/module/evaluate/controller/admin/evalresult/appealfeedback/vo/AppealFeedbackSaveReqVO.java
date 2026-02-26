package cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.appealfeedback.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 申诉反馈新增/修改 Request VO")
@Data
public class AppealFeedbackSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22598")
    private Long id;

    @Schema(description = "申诉反馈UUID（业务主键）", example = "21518")
    private String feedbackId;

    @Schema(description = "申诉复核ID，关联eval_appeal_review.appeal_id", example = "19741")
    private String appealId;

    @Schema(description = "反馈内容")
    private String feedbackContent;

    @Schema(description = "反馈时间")
    private LocalDateTime feedbackTime;

    @Schema(description = "反馈人，关联sys_user.user_id")
    private String feedbackBy;

    @Schema(description = "反馈状态，关联sys_data_status.status_id", example = "1")
    private String status;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}