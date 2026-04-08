package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderlist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 每日订单趋势响应 VO")
@Data
public class OrderListDailyTrendRespVO {

    @Schema(description = "日期", example = "2025-03-01")
    private String date;

    @Schema(description = "当日订单数量", example = "120")
    private Integer orderCount;

    @Schema(description = "当日交易金额", example = "5600.50")
    private BigDecimal tradeAmount;

    @Schema(description = "当日总充电量", example = "3750.20")
    private BigDecimal chargeAmount;
}