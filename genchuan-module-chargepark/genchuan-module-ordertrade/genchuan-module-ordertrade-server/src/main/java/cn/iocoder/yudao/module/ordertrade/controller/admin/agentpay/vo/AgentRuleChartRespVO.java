package cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 代付规则统计 Response VO")
@Data
public class AgentRuleChartRespVO {

    @Schema(description = "规则使用分布数据（柱状图）")
    private List<Map<String, Object>> useDistData;

    @Schema(description = "今日核心指标（卡片）")
    private CardData cardData;

    @Data
    @Schema(description = "卡片指标")
    public static class CardData {
        @Schema(description = "生效规则数")
        private Long enabledCount;
        @Schema(description = "代付订单量（今日）")
        private Long todayOrderCount;
    }
}
