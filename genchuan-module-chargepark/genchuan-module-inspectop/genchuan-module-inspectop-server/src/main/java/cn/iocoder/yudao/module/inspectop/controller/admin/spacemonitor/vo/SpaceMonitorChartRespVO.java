package cn.iocoder.yudao.module.inspectop.controller.admin.spacemonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "巡查巡检 - 车位状态监控 Response VO")
@Data
public class SpaceMonitorChartRespVO {

    @Schema(description = "车位状态分布地图数据")
    private List<MapData> mapData;

    @Schema(description = "状态更新趋势折线图数据")
    private List<TrendData> trendData;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Schema(description = "地图数据项")
    @Data
    public static class MapData {
        @Schema(description = "车位ID", requiredMode = Schema.RequiredMode.REQUIRED)
        private Long id;

        @Schema(description = "车位名称", requiredMode = Schema.RequiredMode.REQUIRED)
        private String name;

        @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED)
        private String status;

        @Schema(description = "经度", requiredMode = Schema.RequiredMode.REQUIRED)
        private BigDecimal longitude;

        @Schema(description = "纬度", requiredMode = Schema.RequiredMode.REQUIRED)
        private BigDecimal latitude;
    }

    @Schema(description = "趋势数据项")
    @Data
    public static class TrendData {
        @Schema(description = "时间点", requiredMode = Schema.RequiredMode.REQUIRED)
        private String time;

        @Schema(description = "正常车位数量", requiredMode = Schema.RequiredMode.REQUIRED)
        private Integer normalCount;

        @Schema(description = "异常车位数量", requiredMode = Schema.RequiredMode.REQUIRED)
        private Integer abnormalCount;
    }

    @Schema(description = "卡片数据")
    @Data
    public static class CardData {
        @Schema(description = "正常车位数量", requiredMode = Schema.RequiredMode.REQUIRED)
        private Integer normalSpace;

        @Schema(description = "异常车位数量", requiredMode = Schema.RequiredMode.REQUIRED)
        private Integer abnormalSpace;
    }
}