package cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
@Schema(description = "充电站图表统计响应 VO")
public class ChargingStationChartRespVO {

    @Schema(description = "场站地图数据列表")
    private List<StationMap> stationMapList;

    @Schema(description = "区域柱状图数据列表")
    private List<AreaBar> areaBarList;

    @Schema(description = "卡片统计信息")
    private CardInfo cardInfo;

    // ==================== 内部类 ====================
    @Data
    public static class StationMap {
        private Long id;
        private String areaid;
        private String stationName;
        private BigDecimal lon;
        private BigDecimal lat;
        private String stationStatus;
    }

    @Data
    public static class AreaBar {
        private String areaName;
        private Integer totalCount;
        private Integer enableCount;
    }

    @Data
    public static class CardInfo {
        private Integer totalCount;
        private Integer enableCount;
        private Integer disableCount;
        private Integer unEnableCount;
    }
}