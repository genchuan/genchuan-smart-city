package cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 商户对账单统计图表 Response VO")
@Data
public class ReconcileBillChartRespVO {

    @Schema(description = "对账单趋势数据（折线图）")
    private List<Map<String, Object>> trendData;

    @Schema(description = "今日核心指标（卡片）")
    private CardData cardData;

    @Data
    @Schema(description = "卡片指标")
    public static class CardData {

        @Schema(description = "待对账对账单数量")
        private Long pendingCount;

        @Schema(description = "异常对账单数量")
        private Long disputedCount;

        @Schema(description = "已对账对账单数量")
        private Long confirmedCount;

        @Schema(description = "对账通过率(%)")
        private BigDecimal confirmRate;
    }
}
