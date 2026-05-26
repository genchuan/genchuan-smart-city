package cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.vehicleaccess.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 停车缴费 Request VO")
@Data
public class VehicleAccessPayReqVO {

    @Schema(description = "通行记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "通行记录ID不能为空")
    private Long id;

    @Schema(description = "支付方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "支付方式不能为空")
    private String payType;

}
