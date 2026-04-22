package cn.iocoder.yudao.module.ordertrade.controller.admin.orderreport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 周期报表图表 Response VO")
@Data
public class CycleReportChartRespVO {

    @Schema(description = "卡片数据（KPI 汇总，实时聚合）")
    private CardData cardData;

    @Schema(description = "折线图数据：订单量趋势、营收趋势、退款金额趋势、异常订单趋势")
    private List<Map<String, Object>> lineData;

    @Schema(description = "柱状图数据：各类型订单量分布、各场站订单量分布")
    private List<Map<String, Object>> barData;

    @Schema(description = "饼图数据：订单状态占比、支付方式占比、异常类型占比")
    private List<Map<String, Object>> pieData;

    @Data
    public static class CardData {
        @Schema(description = "周期订单量")
        private Integer cycleOrderCount;
        @Schema(description = "周期营收（元）")
        private BigDecimal cycleRevenue;
        @Schema(description = "支付率（%）")
        private BigDecimal payRate;
        @Schema(description = "充电量（度）")
        private BigDecimal chargeQuantity;
        @Schema(description = "借出量（次）")
        private Integer lendCount;
        @Schema(description = "退款金额（元）")
        private BigDecimal refundAmount;
        @Schema(description = "待处置异常数")
        private Integer waitHandleAbnormalCount;
        @Schema(description = "追缴完成率（%）")
        private BigDecimal collectCompleteRate;
        @Schema(description = "核算准确率（%）")
        private BigDecimal checkAccuracyRate;
    }
}
