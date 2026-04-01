package cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
@Schema(description = "充电站图表统计响应 VO")
public class ChargingStationChartRespVO {

    @Schema(description = "场站总数量", example = "120")
    private Integer totalCount;

    @Schema(description = "已启用数量", example = "98")
    private Integer enableCount;

    @Schema(description = "已停用数量", example = "12")
    private Integer disableCount;

    @Schema(description = "未启用数量", example = "10")
    private Integer waitCount;

    @Schema(description = "各区域统计（柱状图）")
    private List<AreaStat> areaList;

    @Schema(description = "地图点位数据")
    private List<StationPoint> stationPoints;

    // ========== 内部类 ==========
    @Data
    public static class AreaStat {
        @Schema(description = "区域名称", example = "丰泽区")
        private String areaName;
        @Schema(description = "数量", example = "45")
        private Integer count;
    }

    @Data
    public static class StationPoint {
        @Schema(description = "场站ID", example = "1001")
        private Long id;
        @Schema(description = "场站名称", example = "泉州丰泽万达广场充电站")
        private String stationName;
        @Schema(description = "经度", example = "118.589423")
        private BigDecimal lon;
        @Schema(description = "纬度", example = "24.907856")
        private BigDecimal lat;
        @Schema(description = "状态", example = "enabled")
        private String status;
        @Schema(description = "状态名称", example = "已启用")
        private String statusName;
    }
}