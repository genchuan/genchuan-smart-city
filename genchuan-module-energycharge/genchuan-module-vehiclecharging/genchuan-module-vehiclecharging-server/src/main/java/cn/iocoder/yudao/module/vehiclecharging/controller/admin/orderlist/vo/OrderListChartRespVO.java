package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderlist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 充电订单交易趋势图 Response VO")
@Data
public class OrderListChartRespVO {

    @Schema(description = "折线图数据")
    private List<LineData> lineData;

    @Schema(description = "饼图数据")
    private List<PieData> pieData;

    @Schema(description = "卡片数据")
    private CardData cardData;

    @Data
    public static class LineData {
        @Schema(description = "日期", example = "2025-03-01")
        private String date;
        @Schema(description = "订单数量", example = "120")
        private Integer orderCount;
        @Schema(description = "交易金额", example = "5600.50")
        private BigDecimal tradeAmount;
    }

    @Data
    public static class PieData {
        @Schema(description = "状态名称", example = "已完成")
        private String name;
        @Schema(description = "数量", example = "1200")
        private Integer value;
    }

    @Data
    public static class CardData {
        @Schema(description = "总订单数", example = "1300")
        private Integer totalOrderCount;
        @Schema(description = "总交易金额", example = "58900.75")
        private BigDecimal totalTradeAmount;
        @Schema(description = "总充电量", example = "39200.50")
        private BigDecimal totalChargeAmount;
        @Schema(description = "平均充电时长（分钟）", example = "58")
        private Integer avgChargeTime;
    }
}