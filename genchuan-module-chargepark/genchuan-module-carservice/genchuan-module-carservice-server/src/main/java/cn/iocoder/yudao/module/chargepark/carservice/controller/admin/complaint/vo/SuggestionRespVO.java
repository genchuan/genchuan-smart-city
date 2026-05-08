package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 意见建议 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SuggestionRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "用户 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("用户 ID")
    private Long userId;

    @Schema(description = "用户名（关联 system_user.nickname）")
    @ExcelProperty("用户名")
    private String userName;

    @Schema(description = "意见内容")
    @ExcelProperty("意见内容")
    private String content;

    @Schema(description = "提交时间")
    @ExcelProperty("提交时间")
    private LocalDateTime submitTime;

    @Schema(description = "处理状态,关联字典 suggestion_status")
    @ExcelProperty("处理状态")
    private String status;

    @Schema(description = "处理人 ID")
    @ExcelProperty("处理人 ID")
    private Long handleUserId;

    @Schema(description = "处理人名（关联 system_user.nickname）")
    @ExcelProperty("处理人名")
    private String handleUserName;

    @Schema(description = "处理时间")
    @ExcelProperty("处理时间")
    private LocalDateTime handleTime;

    @Schema(description = "处理进度")
    @ExcelProperty("处理进度")
    private String progress;

    @Schema(description = "反馈内容")
    @ExcelProperty("反馈内容")
    private String feedbackContent;

    @Schema(description = "反馈时间")
    @ExcelProperty("反馈时间")
    private LocalDateTime feedbackTime;

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
