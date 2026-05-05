package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.drill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周期报表钻取-卡种订单量 Response VO")
@Data
public class CycleReportDrillCardOrderCountRespVO {

    @Schema(description = "卡种订单ID")
    private Long id;
    @Schema(description = "订单编号")
    private String no;
    @Schema(description = "用户ID")
    private Long userId;
    @Schema(description = "用户名")
    private String userName;
    @Schema(description = "卡种ID")
    private Long cardId;
    @Schema(description = "卡种名称")
    private String cardName;
    @Schema(description = "卡种类型")
    private String cardType;
    @Schema(description = "订单金额")
    private BigDecimal amount;
    @Schema(description = "支付状态")
    private String payStatus;
    @Schema(description = "支付时间")
    private LocalDateTime payTime;
    @Schema(description = "租户ID")
    private Long tenantId;

}
