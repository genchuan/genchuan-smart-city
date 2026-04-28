package cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 分账比例统计 Response VO")
@Data
public class SplitRateChartRespVO {

    @Schema(description = "分账比例分布数据（柱状图）")
    private List<Map<String, Object>> splitModeData;

    @Schema(description = "今日核心指标（卡片）")
    private CardData cardData;

    @Data
    @Schema(description = "卡片指标")
    public static class CardData {

        @Schema(description = "生效配置数")
        private Long enabledCount;
    }
}
