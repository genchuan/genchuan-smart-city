package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.carddrill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周期报表图表卡片钻取-卡种订单量 Response VO")
@Data
public class CycleReportChartCardDrillCardOrderCountRespVO {

    @Schema(description = "卡种订单ID，关联卡种订单表card_order")
    private Long id;

    @Schema(description = "订单编号，关联card_order.no")
    private String no;

    @Schema(description = "用户ID，关联芋道用户表system_user")
    private Long userId;

    @Schema(description = "用户名，关联system_user.username")
    private String userName;

    @Schema(description = "卡种ID，关联卡种配置表card_config")
    private Long cardId;

    @Schema(description = "卡种名称，关联card_config.name")
    private String cardName;

    @Schema(description = "卡种类型（日卡/周卡/月卡/季卡/年卡，关联芋道字典表：card_config_type）")
    private String cardType;

    @Schema(description = "订单金额，关联card_order.amount")
    private BigDecimal amount;

    @Schema(description = "支付状态（待支付/已支付/已完成/已取消，关联芋道字典表：card_order_pay_status）")
    private String payStatus;

    @Schema(description = "支付时间，关联card_order.pay_time")
    private LocalDateTime payTime;

    @Schema(description = "租户ID，关联card_order.tenant_id")
    private Long tenantId;

}
