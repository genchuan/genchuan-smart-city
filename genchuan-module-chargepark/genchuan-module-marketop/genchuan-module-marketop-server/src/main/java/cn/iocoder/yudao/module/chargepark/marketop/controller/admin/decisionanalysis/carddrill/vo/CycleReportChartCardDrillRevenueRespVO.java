package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.carddrill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周期报表图表卡片钻取-营收 Response VO")
@Data
public class CycleReportChartCardDrillRevenueRespVO {

    @Schema(description = "订单ID，关联卡种订单表card_order")
    private Long orderId;

    @Schema(description = "订单编号，关联card_order.no")
    private String orderNo;

    @Schema(description = "订单金额，关联card_order.amount")
    private BigDecimal orderAmount;

    @Schema(description = "支付时间，关联card_order.pay_time")
    private LocalDateTime payTime;

    @Schema(description = "支付方式")
    private String payType;

    @Schema(description = "用户ID，关联芋道用户表system_user")
    private Long userId;

    @Schema(description = "用户名，关联system_user.username")
    private String userName;

    @Schema(description = "租户ID")
    private Long tenantId;

}
