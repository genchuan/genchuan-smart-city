package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo;

import cn.iocoder.yudao.framework.dict.validation.InDict;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 意见建议新增/修改 Request VO")
@Data
public class SuggestionSaveReqVO {

    @Schema(description = "主键 ID", example = "1024")
    private Long id;

    @Schema(description = "用户 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotNull(message = "用户 ID 不能为空")
    private Long userId;

    @Schema(description = "意见内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "意见内容不能为空")
    private String content;

    @Schema(description = "提交时间（创建时由后端默认为当前时间）")
    private LocalDateTime submitTime;

    @Schema(description = "处理状态,关联字典 suggestion_status(创建时由后端默认为 待处理)",
            example = "待处理",
            allowableValues = {"待处理", "处理中", "已完成"})
    @InDict(type = "suggestion_status")
    private String status;

    @Schema(description = "处理人 ID")
    private Long handleUserId;

    @Schema(description = "处理进度")
    private String progress;

    @Schema(description = "反馈内容")
    private String feedbackContent;

    @Schema(description = "反馈时间")
    private LocalDateTime feedbackTime;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}
