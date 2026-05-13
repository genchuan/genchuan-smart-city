package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.cyclereport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 周期报表图表 Response VO")
@Data
public class CycleReportChartRespVO {

    @Schema(description = "卡片数据")
    private CardData cardData;

    @Schema(description = "折线图数据")
    private List<ChartLineData> lineData;

    @Schema(description = "柱状图数据")
    private List<ChartBarData> barData;

    @Schema(description = "饼图数据")
    private List<ChartPieData> pieData;

    @Data
    public static class CardData {

        @Schema(description = "活动数")
        private Integer activityCount;

        @Schema(description = "参与用户数")
        private Integer joinUserCount;

        @Schema(description = "抽奖量")
        private Integer lotteryCount;

        @Schema(description = "中奖率")
        private BigDecimal winningRate;

        @Schema(description = "优惠券发放量")
        private Integer couponSendCount;

        @Schema(description = "核销率")
        private BigDecimal couponVerifyRate;

        @Schema(description = "卡种订单量")
        private Integer cardOrderCount;

        @Schema(description = "营收")
        private BigDecimal revenue;

        @Schema(description = "兑换量")
        private Integer exchangeCount;

        @Schema(description = "总库存")
        private Integer totalStock;

        @Schema(description = "预警库存数")
        private Integer warnStockCount;

    }

    @Data
    public static class ChartLineData {

        @Schema(description = "趋势名称")
        private String name;

        @Schema(description = "趋势数据")
        private List<ChartLineItemData> data;
    }

    @Data
    public static class ChartLineItemData {

        @Schema(description = "日期")
        private String date;

        @Schema(description = "数量")
        private Integer count;
    }

    @Data
    public static class ChartBarData {

        @Schema(description = "分布名称")
        private String name;

        @Schema(description = "分布数据")
        private List<Map<String, Object>> data;

    }

    @Data
    public static class ChartPieData {

        @Schema(description = "占比名称")
        private String name;

        @Schema(description = "占比数据")
        private List<ChartRatioItem> data;

    }

    @Data
    public static class ChartRatioItem {

        @Schema(description = "类型")
        private String type;

        @Schema(description = "占比")
        private BigDecimal ratio;

    }

}
