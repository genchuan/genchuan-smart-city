// TradeCountRespVO.java
package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderlist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;

@Schema(description = "汽车充电 - 订单交易统计响应 VO")
@Data
public class TradeCountRespVO {

    @Schema(description = "总订单数", example = "1300")
    private Integer totalOrderCount;

    @Schema(description = "总交易金额", example = "58900.75")
    private BigDecimal totalTradeAmount;

    @Schema(description = "总充电量", example = "39200.50")
    private BigDecimal totalChargeAmount;

    @Schema(description = "平均充电时长（分钟）", example = "58")
    private Integer avgChargeTime;

    @Schema(description = "已完成订单数", example = "1200")
    private Integer completeOrderCount;

    @Schema(description = "订单完成率（%）", example = "92.31")
    private BigDecimal completeRatio;
}