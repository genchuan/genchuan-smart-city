package cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingspace.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 车位确认 Request VO")
@Data
public class ParkingSpaceConfirmReqVO {

    @Schema(description = "车位ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "车位ID不能为空")
    private Long id;

}
