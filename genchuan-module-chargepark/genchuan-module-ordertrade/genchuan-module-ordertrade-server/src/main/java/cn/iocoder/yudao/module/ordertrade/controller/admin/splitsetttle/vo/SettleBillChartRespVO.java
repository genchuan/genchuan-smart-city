package cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 结算单据统计 Response VO")
@Data
public class SettleBillChartRespVO {

    @Schema(description = "结算单据趋势数据（折线图）")
    private List<Map<String, Object>> trendData;

    @Schema(description = "今日核心指标（卡片）")
    private CardData cardData;

    @Data
    @Schema(description = "卡片指标")
    public static class CardData {

        @Schema(description = "结算金额")
        private BigDecimal totalSettleAmount;

        @Schema(description = "结算完成率（%）")
        private BigDecimal settleCompleteRate;
    }
}
