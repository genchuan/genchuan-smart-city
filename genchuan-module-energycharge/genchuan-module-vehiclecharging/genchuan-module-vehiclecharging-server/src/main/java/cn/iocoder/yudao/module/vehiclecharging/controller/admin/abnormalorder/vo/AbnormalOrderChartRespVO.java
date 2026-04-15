package cn.iocoder.yudao.module.vehiclecharging.controller.admin.abnormalorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Schema(description = "异常订单统计图表 Response VO")
@Data
public class AbnormalOrderChartRespVO {

    @Schema(description = "柱状图数据")
    private List<BarData> barData;

    @Schema(description = "饼图数据")
    private List<PieData> pieData;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    // 柱状图子结构
    @Data
    public static class BarData {
        @Schema(description = "日期", example = "2025-03-01")
        private String date;
        @Schema(description = "异常订单数", example = "5")
        private Integer abnormalCount;
        @Schema(description = "处理完成数", example = "4")
        private Integer handleCount;
    }

    // 饼图子结构
    @Data
    public static class PieData {
        @Schema(description = "异常类型", example = "充电中断")
        private String name;
        @Schema(description = "数量", example = "15")
        private Integer value;
    }

    // 卡片统计子结构
    @Data
    public static class CardData {
        @Schema(description = "总异常数", example = "32")
        private Integer totalAbnormalCount;
        @Schema(description = "未处理数", example = "5")
        private Integer unHandleCount;
        @Schema(description = "已处理数", example = "27")
        private Integer handleCount;
        @Schema(description = "完成率 %", example = "84.38")
        private Double handleRatio;
    }
}