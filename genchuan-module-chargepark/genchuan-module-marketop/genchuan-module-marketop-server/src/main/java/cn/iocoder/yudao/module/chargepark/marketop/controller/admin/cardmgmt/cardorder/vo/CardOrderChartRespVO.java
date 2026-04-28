package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 卡种订单图表统计 Response VO")
@Data
public class CardOrderChartRespVO {

    @Schema(description = "今日订单数")
    private Integer todayOrderCount;

    @Schema(description = "今日营收")
    private BigDecimal todayRevenue;

    @Schema(description = "订单趋势(近30天)")
    private List<TrendItem> trendList;

    @Schema(description = "卡种类型订单分布")
    private List<TypeCountItem> typeCountList;

    @Data
    public static class TrendItem {
        @Schema(description = "日期")
        private String date;
        @Schema(description = "订单数")
        private Integer count;
        @Schema(description = "营收金额")
        private BigDecimal amount;
    }

    @Data
    public static class TypeCountItem {
        @Schema(description = "卡种类型")
        private String cardType;
        @Schema(description = "订单数")
        private Integer count;
    }

}
