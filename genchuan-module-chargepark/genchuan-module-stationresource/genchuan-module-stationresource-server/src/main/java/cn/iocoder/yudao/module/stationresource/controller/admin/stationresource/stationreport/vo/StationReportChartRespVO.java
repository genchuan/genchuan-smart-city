package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 场站资源报表图表 Response VO
 */
@Data
@Schema(description = "场站资源报表图表")
public class StationReportChartRespVO {

    @Schema(description = "卡片数据")
    private CardData cardData;

    @Schema(description = "地图数据")
    private List<MapData> mapData;

    @Schema(description = "柱状图数据")
    private List<BarData> barData;

    @Schema(description = "折线图数据")
    private List<LineData> lineData;

    @Data
    public static class CardData {
        private Integer totalAreaNum;
        private Integer coverStationNum;
        private Integer totalStationNum;
        private Integer normalOperateStationNum;
        private Integer totalSpaceNum;
        private Integer availableSpaceNum;
        private Integer effectiveRuleNum;
        private Integer orderNum;
        private BigDecimal income;
        private BigDecimal recoveryRate;
        private Integer depositOrderNum;
    }

    @Data
    public static class MapData {
        private String type;
        private String name;
        private BigDecimal lon;
        private BigDecimal lat;
    }

    @Data
    public static class BarData {
        private String name;
        private Integer value;
    }

    @Data
    public static class LineData {
        private String date;
        private Integer orderNum;
    }
}
