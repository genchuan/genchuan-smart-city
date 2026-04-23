package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 场站资源周期报表 Response VO")
@Data
public class StationOpReportChartRespVO {

    @Schema(description = "卡片数据")
    private CardData cardData;

    @Schema(description = "地图数据")
    private List<MapData> mapData;

    @Schema(description = "地图数据-场站")
    private List<StationMapData> stationMapData;

    @Schema(description = "地图数据-车位")
    private List<ParkSpaceMapData> parkSpaceMapData;

    @Schema(description = "柱状图数据")
    private List<BarData> barData;

    @Schema(description = "折线图数据")
    private List<LineData> lineData;

    // ========== 嵌套数据对象 ==========
    @Data
    @Schema(description = "卡片数据")
    public static class CardData {
        @Schema(description = "总片区数", example = "10")
        private Integer totalAreaCount;
        @Schema(description = "覆盖场站数", example = "25")
        private Integer coverStationCount;
        @Schema(description = "总站场数", example = "30")
        private Integer totalStationCount;
        @Schema(description = "正常运营数", example = "28")
        private Integer normalOperateCount;
        @Schema(description = "总车位数", example = "500")
        private Integer totalSpaceCount;
        @Schema(description = "可用车位数", example = "120")
        private Integer availableSpaceCount;
        @Schema(description = "生效规则数", example = "45")
        private Integer effectiveRuleCount;
        @Schema(description = "订单量", example = "12000")
        private Integer orderCount;
        @Schema(description = "营收", example = "150000.00")
        private BigDecimal revenue;
        @Schema(description = "追缴完成率", example = "92.50")
        private BigDecimal recoveryRate;
        @Schema(description = "押金订单量", example = "800")
        private Integer depositOrderCount;
    }

    @Data
    @Schema(description = "地图数据-车位",hidden = true)
    public static class ParkSpaceMapData {
        @Schema(description = "车位名称", example = "丰泽区充电站")
        private String parkSpaceName;
        @Schema(description = "经度", example = "118.675324")
        private BigDecimal longitude;
        @Schema(description = "纬度", example = "24.896541")
        private BigDecimal latitude;
    }
    @Data
    @Schema(description = "地图数据-场站",hidden = true)
    public static class StationMapData {
        @Schema(description = "场站名称", example = "丰泽区充电站")
        private String stationName;
        @Schema(description = "经度", example = "118.675324")
        private BigDecimal longitude;
        @Schema(description = "纬度", example = "24.896541")
        private BigDecimal latitude;
    }

    @Data
    @Schema(description = "地图数据")
    public static class MapData {
        @Schema(description = "片区名称", example = "丰泽区")
        private String areaName;
        @Schema(description = "场站数量", example = "8")
        private Integer stationCount;
        @Schema(description = "经度", example = "118.675324")
        private BigDecimal longitude;
        @Schema(description = "纬度", example = "24.896541")
        private BigDecimal latitude;
    }

    @Data
    @Schema(description = "柱状图数据")
    public static class BarData {
        @Schema(description = "片区名称", example = "丰泽区")
        private String areaName;
        @Schema(description = "场站类型", example = "公共场站")
        private String stationType;
        @Schema(description = "场站数量", example = "8")
        private Integer stationCount;
    }

    @Data
    @Schema(description = "折线图数据")
    public static class LineData {
        @Schema(description = "日期", example = "2026-03-01")
        private String date;
        @Schema(description = "订单量", example = "400")
        private Integer orderCount;
        @Schema(description = "拓场进度", example = "10")
        private Integer expandProgress;
        @Schema(description = "权限使用次数", example = "200")
        private Integer permissionUseCount;
    }
}
