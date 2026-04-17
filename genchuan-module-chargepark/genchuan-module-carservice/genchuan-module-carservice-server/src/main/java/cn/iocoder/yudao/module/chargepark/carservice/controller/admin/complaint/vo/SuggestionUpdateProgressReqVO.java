package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 意见建议更新进度 Request VO")
@Data
public class SuggestionUpdateProgressReqVO {

    @Schema(description = "意见建议记录 ID,仅支持处理中状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "意见 ID 不能为空")
    private Long id;

    @Schema(description = "处理进度描述", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "处理进度不能为空")
    private String progress;

}
