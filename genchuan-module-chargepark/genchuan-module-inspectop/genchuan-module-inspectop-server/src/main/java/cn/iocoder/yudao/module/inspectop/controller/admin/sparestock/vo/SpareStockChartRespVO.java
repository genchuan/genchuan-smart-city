package cn.iocoder.yudao.module.inspectop.controller.admin.sparestock.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Schema(description = "巡查巡检 - 备件仓储图表统计 Response VO")
@Data
public class SpareStockChartRespVO {

    @Schema(description = "库存趋势折线图数据")
    private List<TrendData> trendData;

    @Schema(description = "备件库存分布柱状图数据")
    private List<StockData> stockData;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Data
    @Schema(description = "库存趋势数据")
    public static class TrendData {

        @Schema(description = "时间（月份）", example = "01")
        private String time;

        @Schema(description = "库存数量", example = "80")
        private Integer stockCount;
    }

    @Data
    @Schema(description = "备件库存分布数据")
    public static class StockData {

        @Schema(description = "备件名称", example = "充电枪密封圈")
        private String spareName;

        @Schema(description = "当前库存", example = "13")
        private Integer currentStock;
    }

    @Data
    @Schema(description = "卡片统计数据")
    public static class CardData {

        @Schema(description = "备件库存总量", example = "86")
        private Integer spareStock;

        @Schema(description = "需要补货的备件种类数", example = "2")
        private Integer replenishCount;
    }
}