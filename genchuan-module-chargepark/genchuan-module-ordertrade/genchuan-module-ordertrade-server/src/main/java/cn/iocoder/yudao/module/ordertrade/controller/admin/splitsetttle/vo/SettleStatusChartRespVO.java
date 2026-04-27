package cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 结算状态统计 Response VO")
@Data
public class SettleStatusChartRespVO {

    @Schema(description = "结算状态占比数据（饼图）")
    private List<Map<String, Object>> statusData;

    @Schema(description = "今日核心指标（卡片）")
    private CardData cardData;

    @Data
    @Schema(description = "卡片指标")
    public static class CardData {

        @Schema(description = "结算完成率（%）")
        private BigDecimal completeRate;

        @Schema(description = "异常结算占比（%）")
        private BigDecimal abnormalRate;
    }
}
