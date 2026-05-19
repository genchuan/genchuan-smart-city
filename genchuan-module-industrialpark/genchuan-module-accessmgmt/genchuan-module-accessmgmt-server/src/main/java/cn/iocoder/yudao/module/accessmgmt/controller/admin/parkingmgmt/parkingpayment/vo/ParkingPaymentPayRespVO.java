package cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingpayment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 停车缴费支付 Response VO")
@Data
public class ParkingPaymentPayRespVO {

    @Schema(description = "是否成功")
    private Boolean success;

    @Schema(description = "支付链接")
    private String payUrl;

}
