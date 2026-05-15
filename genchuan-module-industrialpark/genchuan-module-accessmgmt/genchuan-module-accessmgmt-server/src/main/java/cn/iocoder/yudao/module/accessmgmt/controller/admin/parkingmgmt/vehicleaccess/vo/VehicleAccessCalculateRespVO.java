package cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.vehicleaccess.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 费用计算 Response VO")
@Data
public class VehicleAccessCalculateRespVO {

    @Schema(description = "停车时长（分钟）")
    private Integer parkDuration;

    @Schema(description = "费用金额")
    private BigDecimal feeAmount;

}
