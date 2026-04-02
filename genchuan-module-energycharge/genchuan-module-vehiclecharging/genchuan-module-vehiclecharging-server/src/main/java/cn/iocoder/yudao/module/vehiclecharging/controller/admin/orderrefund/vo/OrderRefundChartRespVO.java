package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderrefund.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 订单退款图表统计 Response VO")
@Data
public class OrderRefundChartRespVO {

    @Schema(description = "折线图数据")
    private List<LineData> lineData;

    @Schema(description = "饼图数据")
    private List<PieData> pieData;

    @Schema(description = "卡片数据")
    private CardData cardData;

    @Data
    @Schema(description = "折线图数据项")
    public static class LineData {
        @Schema(description = "日期", example = "2025-03-01")
        private String date;

        @Schema(description = "退款申请数量", example = "3")
        private Integer applyCount;

        @Schema(description = "退款完成数量", example = "2")
        private Integer completeCount;
    }

    @Data
    @Schema(description = "饼图数据项")
    public static class PieData {
        @Schema(description = "退款状态名称", example = "已完成")
        private String name;

        @Schema(description = "数量", example = "12")
        private Integer value;
    }

    @Data
    @Schema(description = "卡片数据")
    public static class CardData {
        @Schema(description = "总退款申请数", example = "18")
        private Integer totalRefundCount;

        @Schema(description = "总退款金额", example = "890.50")
        private BigDecimal totalRefundAmount;

        @Schema(description = "退款完成率", example = "66.67")
        private BigDecimal refundCompleteRatio;
    }
}