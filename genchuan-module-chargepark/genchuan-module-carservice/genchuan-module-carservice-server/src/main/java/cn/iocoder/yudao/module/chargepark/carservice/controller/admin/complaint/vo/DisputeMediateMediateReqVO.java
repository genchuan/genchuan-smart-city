package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 纠纷调解 Request VO")
@Data
public class DisputeMediateMediateReqVO {

    @Schema(description = "纠纷调解记录 ID,仅支持待调解状态",
            requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "纠纷 ID 不能为空")
    private Long id;

}
