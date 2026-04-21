package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 救援信息转派 Request VO")
@Data
public class RescueInfoTransferReqVO {

    @Schema(description = "救援信息 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "救援信息 ID 不能为空")
    private Long id;

    @Schema(description = "新救援人员 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2002")
    @NotNull(message = "新救援人员 ID 不能为空")
    private Long newRescueUserId;

    @Schema(description = "转派理由", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "转派理由不能为空")
    private String transferReason;

}
