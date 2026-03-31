package cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.appealfeedback.vo;


import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 申诉反馈 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AppealFeedbackRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22598")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "申诉反馈UUID（业务主键）", example = "21518")
    @ExcelProperty("申诉反馈UUID（业务主键）")
    private String feedbackId;

    @Schema(description = "申诉复核ID，关联eval_appeal_review.appeal_id", example = "19741")
    @ExcelProperty("申诉复核ID，关联eval_appeal_review.appeal_id")
    private String appealId;

    @Schema(description = "反馈内容")
    @ExcelProperty("反馈内容")
    private String feedbackContent;

    @Schema(description = "反馈时间")
    @ExcelProperty("反馈时间")
    private LocalDateTime feedbackTime;

    @Schema(description = "反馈人，关联sys_user.user_id")
    @ExcelProperty("反馈人，关联sys_user.user_id")
    private String feedbackBy;

    @Schema(description = "反馈状态，关联sys_data_status.status_id", example = "1")
    @ExcelProperty("反馈状态，关联sys_data_status.status_id")
    private String status;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}