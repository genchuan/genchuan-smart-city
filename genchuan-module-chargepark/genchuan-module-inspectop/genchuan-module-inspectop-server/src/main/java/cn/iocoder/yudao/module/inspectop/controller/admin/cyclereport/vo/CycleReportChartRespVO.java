package cn.iocoder.yudao.module.inspectop.controller.admin.cyclereport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "巡查巡检 - 巡检运维报表图表查询 Response VO")
@Data
public class CycleReportChartRespVO {

    @Schema(description = "卡片数据")
    private CardData cardData;

    @Schema(description = "地图数据")
    private List<MapData> mapData;

    @Schema(description = "柱状图数据")
    private List<BarData> barData;

    @Schema(description = "折线图数据")
    private List<LineData> lineData;

    // ========== 内部类定义 ==========

    @Data
    @Schema(description = "卡片数据")
    public static class CardData {
        @Schema(description = "正常设备数", example = "156")
        private Integer normalDeviceNum = 0;

        @Schema(description = "异常设备数", example = "8")
        private Integer abnormalDeviceNum = 0;

        @Schema(description = "巡检任务数", example = "240")
        private Integer inspectTaskNum = 0;

        @Schema(description = "任务完成率", example = "98.5")
        private BigDecimal taskCompleteRate = BigDecimal.ZERO;

        @Schema(description = "油车占位待处置数", example = "12")
        private Integer oilWaitHandleNum = 0;

        @Schema(description = "处置完成率", example = "95.2")
        private BigDecimal oilHandleCompleteRate = BigDecimal.ZERO;

        @Schema(description = "巡检人员在岗数", example = "28")
        private Integer inspectUserOnlineNum = 0;

        @Schema(description = "资产正常数", example = "320")
        private Integer assetNormalNum = 0;

        @Schema(description = "库存预警数", example = "6")
        private Integer stockWarnNum = 0;
    }

    @Data
    @Schema(description = "地图数据")
    public static class MapData {
        @Schema(description = "场站名称", example = "泉州丰泽充电场站")
        private String stationName;

        @Schema(description = "经度", example = "118.675324")
        private BigDecimal longitude;

        @Schema(description = "纬度", example = "24.896541")
        private BigDecimal latitude;

        @Schema(description = "设备状态", example = "正常")
        private String deviceStatus;

        @Schema(description = "巡检人员状态", example = "在线")
        private String userOnlineStatus;
    }

    @Data
    @Schema(description = "柱状图数据")
    public static class BarData {
        @Schema(description = "场站名称", example = "泉州丰泽充电场站")
        private String stationName;

        @Schema(description = "异常设备数", example = "8")
        private Integer abnormalDeviceNum = 0;

        @Schema(description = "巡检任务类型数量", example = "80")
        private Integer taskTypeNum = 0;

        @Schema(description = "油车占位数", example = "12")
        private Integer oilOccupyNum = 0;
    }

    @Data
    @Schema(description = "折线图数据")
    public static class LineData {
        @Schema(description = "日期", example = "2026-01-01")
        private String date;

        @Schema(description = "设备状态更新数量", example = "160")
        private Integer deviceUpdateNum = 0;

        @Schema(description = "任务处理时效(小时)", example = "1.2")
        private BigDecimal taskHandleTime = BigDecimal.ZERO;

        @Schema(description = "上报数量", example = "5")
        private Integer reportNum = 0;
    }
}