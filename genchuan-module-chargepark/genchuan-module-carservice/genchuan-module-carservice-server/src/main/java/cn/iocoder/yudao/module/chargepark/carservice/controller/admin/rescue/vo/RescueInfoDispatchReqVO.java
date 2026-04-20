package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 救援信息派发 Request VO")
@Data
public class RescueInfoDispatchReqVO {

    @Schema(description = "救援信息 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "救援信息 ID 不能为空")
    private Long id;

    @Schema(description = "救援人员 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2001")
    @NotNull(message = "救援人员 ID 不能为空")
    private Long rescueUserId;

    @Schema(description = "派发备注")
    private String dispatchRemark;

}
