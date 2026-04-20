package cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.bikechargemonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "巡查巡检 - 两轮充电监测图表 Response VO")
@Data
public class BikeChargeMonitorChartRespVO {

    @Schema(description = "地图数据")
    private List<MapData> mapData;

    @Schema(description = "趋势数据")
    private List<TrendData> trendData;

    @Schema(description = "卡片数据")
    private CardData cardData;

    @Data
    @Schema(description = "地图数据")
    public static class MapData {
        @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED)
        private Long id;

        @Schema(description = "设备名称", requiredMode = Schema.RequiredMode.REQUIRED)
        private String name;

        @Schema(description = "设备状态", requiredMode = Schema.RequiredMode.REQUIRED)
        private String status;

        @Schema(description = "经度", requiredMode = Schema.RequiredMode.REQUIRED)
        private BigDecimal lon;

        @Schema(description = "纬度", requiredMode = Schema.RequiredMode.REQUIRED)
        private BigDecimal lat;
    }

    @Data
    @Schema(description = "趋势数据")
    public static class TrendData {
        @Schema(description = "时间（小时）", requiredMode = Schema.RequiredMode.REQUIRED, example = "01")
        private String time;

        @Schema(description = "正常设备数量", requiredMode = Schema.RequiredMode.REQUIRED)
        private Integer normalCount;

        @Schema(description = "异常设备数量", requiredMode = Schema.RequiredMode.REQUIRED)
        private Integer abnormalCount;
    }

    @Data
    @Schema(description = "卡片数据")
    public static class CardData {
        @Schema(description = "正常设备数量", requiredMode = Schema.RequiredMode.REQUIRED)
        private Integer normalDevice;

        @Schema(description = "异常设备数量", requiredMode = Schema.RequiredMode.REQUIRED)
        private Integer abnormalDevice;
    }
}