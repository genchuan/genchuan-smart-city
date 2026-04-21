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
    private Integer warnCount;

    @Schema(description = "库存变化趋势(近30天)")
    private List<TrendItem> trendList;

    @Schema(description = "各卡种库存分布")
    private List<StockItem> stockList;

    @Data
    public static class TrendItem {
        @Schema(description = "日期")
        private String date;
        @Schema(description = "库存量")
        private Integer count;
    }

    @Data
    public static class StockItem {
        @Schema(description = "卡种ID")
        private Long cardId;
        @Schema(description = "卡种名称")
        private String cardName;
        @Schema(description = "当前库存")
        private Integer currentStock;
        @Schema(description = "预警阈值")
        private Integer warnThreshold;
    }

}
