package cn.iocoder.yudao.module.vehiclecharging.controller.admin.pilealarm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@Schema(description = "充电桩告警图表 Response VO")
public class PileAlarmChartRespVO {

    @Schema(description = "总告警数")
    private Integer totalCount;

    @Schema(description = "已处置数")
    private Integer handledCount;

    @Schema(description = "处置率")
    private BigDecimal handleRate;

    @Schema(description = "柱状图（按天）")
    private List<BarData> barData;

    @Schema(description = "饼图（故障类型）")
    private List<PieData> pieData;

    @Schema(description = "状态卡片")
    private CardData cardData;

    @Data
    public static class BarData {
        private String date;
        private Integer alarmCount;
        private Integer handleCount;
    }

    @Data
    public static class PieData {
        private String name;
        private Integer value;
    }

    @Data
    public static class CardData {
        private Integer unDisCount;    // 未派单
        private Integer disCount;      // 已派单
        private Integer handlingCount; // 处置中
        private Integer closedCount;   // 已销单
    }

}