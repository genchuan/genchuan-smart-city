package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.enterrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "入场记录图表统计 - 总返回")
@Data
public class EnterRecordChartRespVO {

    @Schema(description = "入场量趋势（折线图）")
    private List<EnterCountTrend> enterCountTrend;

    @Schema(description = "各时段入场量（柱状图）")
    private List<HourEnterCount> hourEnterCount;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    // ==================== 内部类合并 ====================

    @Schema(description = "入场量趋势（折线图）")
    @Data
    public static class EnterCountTrend {
        @Schema(description = "日期 yyyy-MM-dd")
        private String date;

        @Schema(description = "入场数量")
        private Integer count;
    }

    @Schema(description = "时段入场量（柱状图）")
    @Data
    public static class HourEnterCount {
        @Schema(description = "时段 HH:00")
        private String hour;

        @Schema(description = "入场数量")
        private Integer count;
    }

    @Schema(description = "卡片统计数据")
    @Data
    public static class CardData {
        @Schema(description = "今日入场量")
        private Integer todayEnterCount;

        @Schema(description = "入场峰值")
        private Integer enterPeak;
    }
}