package cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 周期报表图表 Response VO")
@Data
public class CycleReportChartRespVO {

    @Schema(description = "卡片指标数据")
    private CardData cardData;

    @Schema(description = "地图数据")
    private List<MapData> mapData;

    @Schema(description = "柱状图数据")
    private List<BarData> barData;

    @Schema(description = "折线图数据")
    private List<LineData> lineData;

    @Schema(description = "饼图数据")
    private List<PieData> pieData;

    @Data
    @Schema(description = "卡片指标")
    public static class CardData {
        @Schema(description = "入场量")
        private Integer enterCount;
        @Schema(description = "离场量")
        private Integer leaveCount;
        @Schema(description = "在停车辆数")
        private Integer parkingCount;
        @Schema(description = "识别成功率")
        private BigDecimal identifySuccessRate;
        @Schema(description = "核验成功率")
        private BigDecimal checkSuccessRate;
        @Schema(description = "异常处置率")
        private BigDecimal abnormalHandleRate;
        @Schema(description = "ETC通行成功率")
        private BigDecimal etcPassSuccessRate;
    }

    @Data
    @Schema(description = "地图数据")
    public static class MapData {
        @Schema(description = "场站名称")
        private String stationName;
        @Schema(description = "在停车辆数")
        private Integer parkingCount;
        @Schema(description = "通行量")
        private Integer passCount;
        @Schema(description = "泊位使用率")
        private BigDecimal spaceUseRate;
    }

    @Data
    @Schema(description = "柱状图数据")
    public static class BarData {
        @Schema(description = "场站名称")
        private String stationName;
        @Schema(description = "通行量")
        private Integer passCount;
        @Schema(description = "异常数")
        private Integer abnormalCount;
        @Schema(description = "ETC通行量")
        private Integer etcPassCount;
    }

    @Data
    @Schema(description = "折线图数据")
    public static class LineData {
        @Schema(description = "统计时间")
        private String statTime;
        @Schema(description = "通行量")
        private Integer passCount;
        @Schema(description = "识别成功率")
        private BigDecimal identifySuccessRate;
        @Schema(description = "异常处置率")
        private BigDecimal abnormalHandleRate;
        @Schema(description = "核验成功率")
        private BigDecimal checkSuccessRate;
    }

    @Data
    @Schema(description = "饼图数据")
    public static class PieData {
        @Schema(description = "类型")
        private String type;
        @Schema(description = "数量")
        private Integer count;
    }
}