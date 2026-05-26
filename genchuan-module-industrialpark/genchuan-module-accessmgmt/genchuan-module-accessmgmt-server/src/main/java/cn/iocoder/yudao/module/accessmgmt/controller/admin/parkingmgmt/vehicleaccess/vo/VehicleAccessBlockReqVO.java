package cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.vehicleaccess.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 车辆拦截 Request VO")
@Data
public class VehicleAccessBlockReqVO {

    @Schema(description = "通行记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "通行记录ID不能为空")
    private Long id;

    @Schema(description = "拦截原因", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "拦截原因不能为空")
    private String blockReason;

}
