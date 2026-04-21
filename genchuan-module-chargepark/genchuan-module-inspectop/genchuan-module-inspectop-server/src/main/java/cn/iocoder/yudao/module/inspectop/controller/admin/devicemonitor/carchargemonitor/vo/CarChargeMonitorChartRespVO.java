package cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.carchargemonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "巡查巡检 - 汽车充电监测图表 Response VO")
@Data
public class CarChargeMonitorChartRespVO {

    @Schema(description = "充电设备状态分布地图数据")
    private List<MapData> mapData;

    @Schema(description = "状态更新趋势折线图数据")
    private List<TrendData> trendData;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Schema(description = "地图数据项")
    @Data
    public static class MapData {
        @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
        private Long id;

        @Schema(description = "设备名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "CC-01")
        private String name;

        @Schema(description = "设备状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "异常")
        private String status;

        @Schema(description = "经度", requiredMode = Schema.RequiredMode.REQUIRED, example = "118.675324")
        private BigDecimal lon;

        @Schema(description = "纬度", requiredMode = Schema.RequiredMode.REQUIRED, example = "24.896541")
        private BigDecimal lat;
    }

    @Schema(description = "趋势数据项")
    @Data
    public static class TrendData {
        @Schema(description = "时间点（小时）", requiredMode = Schema.RequiredMode.REQUIRED, example = "01")
        private String time;

        @Schema(description = "正常设备数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "60")
        private Integer normalCount;

        @Schema(description = "异常设备数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
        private Integer abnormalCount;
    }

    @Schema(description = "卡片数据")
    @Data
    public static class CardData {
        @Schema(description = "正常设备数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "110")
        private Integer normalDevice;

        @Schema(description = "异常设备数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "3")
        private Integer abnormalDevice;
    }
}