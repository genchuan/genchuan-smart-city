package cn.iocoder.yudao.module.studentmgmt.controller.admin.communicatemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 沟通管理反馈 Request VO")
@Data
public class CommunicateMgmtReplyReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2,5")
    private Long id;

    @Schema(description = "反馈时间",requiredMode = Schema.RequiredMode.REQUIRED, example = "1776220634000")
    @NotNull(message = "反馈时间不能为空")
    private LocalDateTime replyTime;

    @Schema(description = "反馈内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "反馈内容")
    @NotEmpty(message = "反馈内容不能为空")
    private String replyContent;

}