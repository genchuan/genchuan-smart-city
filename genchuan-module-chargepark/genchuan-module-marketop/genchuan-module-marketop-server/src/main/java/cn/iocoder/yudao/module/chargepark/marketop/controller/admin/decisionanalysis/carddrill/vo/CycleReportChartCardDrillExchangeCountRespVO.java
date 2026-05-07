package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.carddrill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周期报表图表卡片钻取-兑换量 Response VO")
@Data
public class CycleReportChartCardDrillExchangeCountRespVO {

    @Schema(description = "兑换订单ID，关联兑换订单表exchange_order")
    private Long id;

    @Schema(description = "订单编号，关联exchange_order.no")
    private String no;

    @Schema(description = "用户ID，关联芋道用户表system_user")
    private Long userId;

    @Schema(description = "用户名，关联system_user.username")
    private String userName;

    @Schema(description = "商品名称，关联exchange_order.goods_name")
    private String goodsName;

    @Schema(description = "消耗积分，关联exchange_order.cost_point")
    private Integer costPoint;

    @Schema(description = "支付状态（待支付/已支付/已完成/已取消，关联芋道字典表：exchange_order_pay_status）")
    private String payStatus;

    @Schema(description = "兑换时间，关联exchange_order.create_time")
    private LocalDateTime exchangeTime;

    @Schema(description = "租户ID，关联exchange_order.tenant_id")
    private Long tenantId;

}
