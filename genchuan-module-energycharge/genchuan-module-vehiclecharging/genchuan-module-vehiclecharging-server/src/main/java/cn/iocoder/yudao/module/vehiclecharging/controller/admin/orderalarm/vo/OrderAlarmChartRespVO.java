package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderalarm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "汽车充电 - 订单告警图表统计 Response VO")
@Data
public class OrderAlarmChartRespVO {

    @Schema(description = "总告警数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "85")
    private Integer totalCount;

    @Schema(description = "已处理告警数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "78")
    private Integer handledCount;

    @Schema(description = "处理率，保留2位小数", requiredMode = Schema.RequiredMode.REQUIRED, example = "0.92")
    private BigDecimal handleRate;

    @Schema(description = "折线图数据")
    private List<LineData> lineData;

    @Schema(description = "饼图数据")
    private List<PieData> pieData;

    @Schema(description = "卡片数据")
    private CardData cardData;

    @Data
    @Schema(description = "折线图数据项")
    public static class LineData {
        @Schema(description = "日期，格式：yyyy-MM-dd", requiredMode = Schema.RequiredMode.REQUIRED, example = "2025-03-01")
        private String date;

        @Schema(description = "告警数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "4")
        private Integer alarmCount;

        @Schema(description = "处理数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "4")
        private Integer handleCount;
    }

    @Data
    @Schema(description = "饼图数据项")
    public static class PieData {
        @Schema(description = "异常原因名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "支付异常")
        private String name;

        @Schema(description = "数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "25")
        private Integer value;
    }

    @Data
    @Schema(description = "卡片数据")
    public static class CardData {
        @Schema(description = "未核实告警数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "5")
        private Integer unVerifyCount;

        @Schema(description = "已核实告警数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
        private Integer verifiedCount;

        @Schema(description = "处理中告警数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
        private Integer handlingCount;

        @Schema(description = "已完结告警数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "78")
        private Integer completedCount;
    }
}