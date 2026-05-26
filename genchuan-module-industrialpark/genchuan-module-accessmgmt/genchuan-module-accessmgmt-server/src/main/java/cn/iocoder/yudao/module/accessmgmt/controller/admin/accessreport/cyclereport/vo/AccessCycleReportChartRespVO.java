package cn.iocoder.yudao.module.accessmgmt.controller.admin.accessreport.cyclereport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 通行周期报表态势 Response VO")
@Data
public class AccessCycleReportChartRespVO {

    @Schema(description = "卡片数据")
    private CardData cardData;

    @Schema(description = "人员通行趋势列表")
    private List<AccessTrendItem> accessTrendList;

    @Schema(description = "车辆通行趋势列表")
    private List<VehicleTrendItem> vehicleTrendList;

    @Schema(description = "车位使用趋势列表")
    private List<SpaceTrendItem> spaceTrendList;

    @Schema(description = "各区域通行次数统计列表")
    private List<AreaCountItem> areaCountList;

    @Schema(description = "各车场停车次数统计列表")
    private List<ParkCountItem> parkCountList;

    @Schema(description = "人员类型分布列表")
    private List<NameValueItem> personTypeList;

    @Schema(description = "车辆类型分布列表")
    private List<NameValueItem> vehicleTypeList;

    @Schema(description = "支付方式分布列表")
    private List<NameValueItem> payTypeList;

    @Schema(description = "点位热力分布列表")
    private List<PointMapItem> pointMapList;

    @Schema(description = "轨迹地图列表")
    private List<TrackMapItem> trackMapList;

    @Schema(description = "卡片数据")
    @Data
    public static class CardData {
        @Schema(description = "人员通行总量")
        private Integer totalPersonAccess;
        @Schema(description = "访客到访总量")
        private Integer totalVisitorArrive;
        @Schema(description = "车辆通行总量")
        private Integer totalVehicleAccess;
        @Schema(description = "车位使用率")
        private BigDecimal spaceUseRate;
        @Schema(description = "缴费收入")
        private BigDecimal payIncome;
    }

    @Schema(description = "人员通行趋势项")
    @Data
    public static class AccessTrendItem {
        @Schema(description = "日期")
        private String date;
        @Schema(description = "人员通行数量")
        private Integer personCount;
        @Schema(description = "访客到访数量")
        private Integer visitorCount;
    }

    @Schema(description = "车辆通行趋势项")
    @Data
    public static class VehicleTrendItem {
        @Schema(description = "日期")
        private String date;
        @Schema(description = "进场数量")
        private Integer inCount;
        @Schema(description = "出场数量")
        private Integer outCount;
    }

    @Schema(description = "车位使用趋势项")
    @Data
    public static class SpaceTrendItem {
        @Schema(description = "日期")
        private String date;
        @Schema(description = "使用率")
        private BigDecimal useRate;
    }

    @Schema(description = "区域统计项")
    @Data
    public static class AreaCountItem {
        @Schema(description = "区域")
        private String area;
        @Schema(description = "通行次数")
        private Integer count;
    }

    @Schema(description = "车场统计项")
    @Data
    public static class ParkCountItem {
        @Schema(description = "车场名称")
        private String parkName;
        @Schema(description = "停车次数")
        private Integer count;
    }

    @Schema(description = "名称数值项（通用饼图数据）")
    @Data
    public static class NameValueItem {
        @Schema(description = "名称")
        private String name;
        @Schema(description = "数值")
        private Integer value;
    }

    @Schema(description = "点位热力项")
    @Data
    public static class PointMapItem {
        @Schema(description = "经度")
        private Double lon;
        @Schema(description = "纬度")
        private Double lat;
        @Schema(description = "数量")
        private Integer count;
    }

    @Schema(description = "轨迹地图项")
    @Data
    public static class TrackMapItem {
        @Schema(description = "经度")
        private Double lon;
        @Schema(description = "纬度")
        private Double lat;
        @Schema(description = "车牌号")
        private String plateNo;
    }

}
