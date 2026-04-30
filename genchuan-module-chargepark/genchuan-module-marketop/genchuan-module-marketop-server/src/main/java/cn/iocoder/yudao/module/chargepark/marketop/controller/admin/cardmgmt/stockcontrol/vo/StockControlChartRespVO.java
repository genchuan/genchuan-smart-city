package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 库存管控图表统计 Response VO")
@Data
public class StockControlChartRespVO {

    @Schema(description = "总库存量")
    private Integer totalStock;

    @Schema(description = "预警数量")
    private Integer warnStockCount;

    @Schema(description = "库存变化趋势")
    private List<TrendItem> stockTrend;

    @Schema(description = "各卡种库存分布(按cardId分类)")
    private List<DistributionItem> stockDistribution;

    @Data
    public static class TrendItem {
        @Schema(description = "日期")
        private String date;
        @Schema(description = "库存量")
        private Integer count;
    }

    @Data
    public static class DistributionItem {
        @Schema(description = "卡种ID")
        private String cardId;
        @Schema(description = "数量")
        private Integer count;
    }

}
