package cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingpayment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 停车缴费生成账单 Response VO")
@Data
public class ParkingPaymentGenerateRespVO {

    @Schema(description = "是否成功")
    private Boolean success;

    @Schema(description = "账单编号")
    private String billCode;

}
