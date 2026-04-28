package cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 代付码统计 Response VO")
@Data
public class AgentCodeChartRespVO {

    @Schema(description = "代付码生成趋势数据（折线图）")
    private List<Map<String, Object>> trendData;

    @Schema(description = "今日核心指标（卡片）")
    private CardData cardData;

    @Data
    @Schema(description = "卡片指标")
    public static class CardData {

        @Schema(description = "今日生成量")
        private Long todayGeneratedCount;

        @Schema(description = "使用率（%）")
        private BigDecimal useRate;
    }
}
