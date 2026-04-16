package cn.iocoder.yudao.module.inspectop.controller.admin.oilmonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "巡查巡检 - 油车占位监控图表 Response VO")
@Data
public class OilMonitorChartRespVO {

    @Schema(description = "占位监测趋势折线图数据")
    private List<TrendData> trendData;

    @Schema(description = "各场站占位数柱状图数据")
    private List<StationData> stationData;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Schema(description = "趋势数据项")
    @Data
    public static class TrendData {
        @Schema(description = "时间点（小时）", requiredMode = Schema.RequiredMode.REQUIRED, example = "01")
        private String time;

        @Schema(description = "识别次数", requiredMode = Schema.RequiredMode.REQUIRED, example = "5")
        private Integer identifyCount;
    }

    @Schema(description = "场站数据项")
    @Data
    public static class StationData {
        @Schema(description = "场站名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "丰泽站")
        private String stationName;

        @Schema(description = "占位次数", requiredMode = Schema.RequiredMode.REQUIRED, example = "12")
        private Integer count;
    }

    @Schema(description = "卡片数据")
    @Data
    public static class CardData {
        @Schema(description = "待处置数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "6")
        private Integer waitProcessCount;

        @Schema(description = "处置完成率 (0-1之间的小数)", requiredMode = Schema.RequiredMode.REQUIRED, example = "0.85")
        private BigDecimal processFinishRate;
    }
}