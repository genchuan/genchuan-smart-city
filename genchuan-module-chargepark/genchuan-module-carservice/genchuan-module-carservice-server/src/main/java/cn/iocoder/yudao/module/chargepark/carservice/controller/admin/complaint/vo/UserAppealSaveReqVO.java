package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 用户申诉新增/修改 Request VO")
@Data
public class UserAppealSaveReqVO {

    @Schema(description = "主键 ID", example = "1024")
    private Long id;

    @Schema(description = "用户 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotNull(message = "用户 ID 不能为空")
    private Long userId;

    @Schema(description = "订单 ID")
    private Long orderId;

    @Schema(description = "申诉内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "申诉内容不能为空")
    private String content;

    @Schema(description = "提交时间（创建时由后端默认为当前时间）")
    private LocalDateTime submitTime;

    @Schema(description = "申诉状态（创建时由后端默认为 待审核）", example = "待审核")
    private String status;

    @Schema(description = "审核人 ID")
    private Long auditUserId;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "处置人 ID")
    private Long handleUserId;

    @Schema(description = "处置进度")
    private String progress;

    @Schema(description = "反馈内容")
    private String feedbackContent;

    @Schema(description = "反馈时间")
    private LocalDateTime feedbackTime;

    @Schema(description = "审核备注")
    private String auditRemark;

    @Schema(description = "驳回理由")
    private String rejectReason;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}
