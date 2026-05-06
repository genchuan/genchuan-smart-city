package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 用户申诉 Response VO")
@Data
@ExcelIgnoreUnannotated
public class UserAppealRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "用户 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("用户 ID")
    private Long userId;

    @Schema(description = "用户名（关联 system_user.nickname）")
    @ExcelProperty("用户名")
    private String userName;

    @Schema(description = "订单 ID")
    @ExcelProperty("订单 ID")
    private Long orderId;

    @Schema(description = "申诉内容")
    @ExcelProperty("申诉内容")
    private String content;

    @Schema(description = "提交时间")
    @ExcelProperty("提交时间")
    private LocalDateTime submitTime;

    @Schema(description = "申诉状态,关联字典 user_appeal_status")
    @ExcelProperty("申诉状态")
    private String status;

    @Schema(description = "审核人 ID")
    @ExcelProperty("审核人 ID")
    private Long auditUserId;

    @Schema(description = "审核人名（关联 system_user.nickname）")
    @ExcelProperty("审核人名")
    private String auditUserName;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "处置人 ID")
    @ExcelProperty("处置人 ID")
    private Long handleUserId;

    @Schema(description = "处置人名（关联 system_user.nickname）")
    @ExcelProperty("处置人名")
    private String handleUserName;

    @Schema(description = "处置进度")
    @ExcelProperty("处置进度")
    private String progress;

    @Schema(description = "反馈内容")
    @ExcelProperty("反馈内容")
    private String feedbackContent;

    @Schema(description = "反馈时间")
    @ExcelProperty("反馈时间")
    private LocalDateTime feedbackTime;

    @Schema(description = "审核备注")
    @ExcelProperty("审核备注")
    private String auditRemark;

    @Schema(description = "驳回理由")
    @ExcelProperty("驳回理由")
    private String rejectReason;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}
