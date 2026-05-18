package cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingpayment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 停车费用计算 Response VO")
@Data
public class ParkingPaymentCalculateRespVO {

    @Schema(description = "原始费用")
    private BigDecimal originalFee;

    @Schema(description = "优惠费用")
    private BigDecimal discountFee;

    @Schema(description = "实付费用")
    private BigDecimal actualFee;

}
