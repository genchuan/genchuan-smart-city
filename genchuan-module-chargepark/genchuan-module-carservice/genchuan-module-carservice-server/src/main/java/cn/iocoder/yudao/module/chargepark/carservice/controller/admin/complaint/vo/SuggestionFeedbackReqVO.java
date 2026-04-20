package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 意见建议反馈 Request VO")
@Data
public class SuggestionFeedbackReqVO {

    @Schema(description = "意见 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "意见 ID 不能为空")
    private Long id;

    @Schema(description = "反馈内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "反馈内容不能为空")
    private String feedbackContent;

}
