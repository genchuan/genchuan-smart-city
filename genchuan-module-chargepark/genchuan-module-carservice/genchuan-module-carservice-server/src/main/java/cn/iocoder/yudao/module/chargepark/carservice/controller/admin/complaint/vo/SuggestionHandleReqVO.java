package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 意见建议处理 Request VO")
@Data
public class SuggestionHandleReqVO {

    @Schema(description = "意见建议记录 ID,仅支持待处理状态",
            requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "意见 ID 不能为空")
    private Long id;

}
