package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 兑换订单图表统计 Response VO")
@Data
public class ExchangeOrderChartRespVO {

    @Schema(description = "今日订单量")
    private Integer todayOrderCount;

    @Schema(description = "今日兑换量")
    private Integer todayExchangeCount;

    @Schema(description = "订单量趋势数据")
    private List<TrendItem> trendList;

    @Schema(description = "类目订单分布数据")
    private List<TypeItem> typeList;

    @Data
    public static class TrendItem {
        @Schema(description = "日期")
        private String date;
        @Schema(description = "订单数量")
        private Integer count;
    }

    @Data
    public static class TypeItem {
        @Schema(description = "类目名称")
        private String categoryName;
        @Schema(description = "订单数量")
        private Integer count;
    }

}
