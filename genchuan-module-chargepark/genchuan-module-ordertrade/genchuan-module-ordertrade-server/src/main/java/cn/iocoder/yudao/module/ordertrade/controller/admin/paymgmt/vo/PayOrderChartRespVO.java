package cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 支付订单统计 Response VO")
@Data
public class PayOrderChartRespVO {

    @Schema(description = "支付订单趋势数据（折线图）")
    private List<Map<String, Object>> trendData;

    @Schema(description = "支付渠道分布数据（柱状图）")
    private List<Map<String, Object>> channelData;

    @Schema(description = "今日核心指标（卡片）")
    private CardData cardData;

    @Data
    @Schema(description = "卡片指标")
    public static class CardData {
        @Schema(description = "今日支付订单量")
        private Long todayOrderCount;
        @Schema(description = "今日支付成功率（%）")
        private BigDecimal successRate;
    }
}
