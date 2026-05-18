package cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingspace.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 车位预约 Request VO")
@Data
public class ParkingSpaceReserveReqVO {

    @Schema(description = "车位ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "车位ID不能为空")
    private Long id;

    @Schema(description = "预约用户", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "预约用户不能为空")
    private String orderUser;

}
