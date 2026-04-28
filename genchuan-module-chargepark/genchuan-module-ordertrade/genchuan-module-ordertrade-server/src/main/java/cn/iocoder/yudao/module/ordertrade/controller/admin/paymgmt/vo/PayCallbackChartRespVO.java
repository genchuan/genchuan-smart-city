package cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 回调通知统计 Response VO")
@Data
public class PayCallbackChartRespVO {

    @Schema(description = "通知量趋势数据（折线图）")
    private List<Map<String, Object>> trendData;

    @Schema(description = "今日核心指标（卡片）")
    private CardData cardData;

    @Data
    @Schema(description = "卡片指标")
    public static class CardData {

        @Schema(description = "今日通知量")
        private Long todayNotifyCount;

        @Schema(description = "处理成功率（%）")
        private BigDecimal successRate;
    }
}
