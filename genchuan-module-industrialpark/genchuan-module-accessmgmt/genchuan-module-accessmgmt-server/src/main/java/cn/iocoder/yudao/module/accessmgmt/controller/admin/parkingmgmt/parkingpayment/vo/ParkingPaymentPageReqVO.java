package cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingpayment.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 停车缴费分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkingPaymentPageReqVO extends PageParam {

    @Schema(description = "账单编号，支持模糊查询")
    private String billCode;

    @Schema(description = "车牌号，支持模糊查询")
    private String plateNo;

    @Schema(description = "账单状态（待缴费/已缴费/已欠费）")
    private String billStatus;

    @Schema(description = "支付方式（微信/支付宝/现金）")
    private String payType;

    @Schema(description = "发票状态（已开具/未开具）")
    private String invoiceStatus;

    @Schema(description = "支付开始时间，格式时间戳")
    private String startTime;

    @Schema(description = "支付结束时间，格式时间戳")
    private String endTime;

}
