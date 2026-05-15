package cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.vehicleaccess.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 车辆通行态势 Response VO")
@Data
public class VehicleAccessChartRespVO {

    @Schema(description = "各时段通行量趋势列表")
    private List<TimeTrendItem> timeTrendList;

    @Schema(description = "每日进出数量趋势列表")
    private List<DayTrendItem> dayTrendList;

    @Schema(description = "各停车场通行数量列表")
    private List<ParkCountItem> parkCountList;

    @Schema(description = "各车辆类型数量列表")
    private List<VehicleTypeItem> vehicleTypeList;

    @Schema(description = "时段趋势项")
    @Data
    public static class TimeTrendItem {
        @Schema(description = "时段")
        private String time;
        @Schema(description = "数量")
        private Integer count;
    }

    @Schema(description = "每日趋势项")
    @Data
    public static class DayTrendItem {
        @Schema(description = "日期")
        private String date;
        @Schema(description = "进场数量")
        private Integer inCount;
        @Schema(description = "出场数量")
        private Integer outCount;
    }

    @Schema(description = "停车场通行统计项")
    @Data
    public static class ParkCountItem {
        @Schema(description = "停车场名称")
        private String parkName;
        @Schema(description = "通行数量")
        private Integer count;
    }

    @Schema(description = "车辆类型统计项")
    @Data
    public static class VehicleTypeItem {
        @Schema(description = "名称")
        private String name;
        @Schema(description = "数值")
        private Integer value;
    }

}
