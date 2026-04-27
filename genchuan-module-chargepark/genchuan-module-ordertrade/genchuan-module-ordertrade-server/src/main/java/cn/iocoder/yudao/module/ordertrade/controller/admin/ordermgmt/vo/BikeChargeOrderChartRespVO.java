package cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 两轮充电订单统计 Response VO
 * @author genchuan
 */
@Schema(description = "管理后台 - 两轮充电订单统计 Response VO")
@Data
public class BikeChargeOrderChartRespVO {

    @Schema(description = "订单量趋势数据（折线图）")
    private List<Map<String,Object>> trendData;

    @Schema(description = "各场站订单量数据（柱状图）")
    private List<Map<String,Object>> stationData;

    @Schema(description = "今日核心指标（卡片）")
    private CardData cardData;

    @Data
    @Schema(description = "卡片指标")
    public static class CardData {
        @Schema(description = "今日订单量")
        private Integer todayOrderCount;
        @Schema(description = "今日充电量（度）")
        private BigDecimal todayChargeQuantity;
        @Schema(description = "今日营收")
        private BigDecimal todayRevenue;
    }
}
