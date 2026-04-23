package cn.iocoder.yudao.module.inspectop.controller.admin.assetstock.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;

@Schema(description = "巡查巡检 - 库存统计图表 Response VO")
@Data
public class AssetStockChartRespVO {

    @Schema(description = "库存趋势折线图数据")
    private List<TrendData> trendData;

    @Schema(description = "资产库存分布柱状图数据")
    private List<StockData> stockData;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    // 库存趋势折线图数据
    @Data
    public static class TrendData {

        @Schema(description = "时间", example = "01")
        private String time;

        @Schema(description = "库存数量", example = "100")
        private Integer stockCount;
    }

    // 资产库存分布柱状图数据
    @Data
    public static class StockData {

        @Schema(description = "资产名称", example = "监测摄像头")
        private String assetName;

        @Schema(description = "当前库存", example = "20")
        private Integer currentStock;
    }

    // 卡片统计数据
    @Data
    public static class CardData {

        @Schema(description = "总库存数量", example = "120")
        private Integer totalStock;

        @Schema(description = "预警库存数量", example = "3")
        private Integer warnStockCount;
    }
}