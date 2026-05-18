package cn.iocoder.yudao.module.integratedsecurity.controller.admin.videomonitor.realtimemonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 实时监控态势图表 Response VO")
@Data
public class RealTimeMonitorChartRespVO {

    @Schema(description = "在线摄像头数量", example = "28")
    private Integer onlineCount;

    @Schema(description = "离线摄像头数量", example = "2")
    private Integer offlineCount;

    @Schema(description = "告警总数", example = "5")
    private Integer alarmTotalCount;

    @Schema(description = "处置完成数", example = "3")
    private Integer handleCompleteCount;

    @Schema(description = "区域统计列表")
    private List<AreaStat> areaStatList;

    @Schema(description = "摄像头地图点位列表")
    private List<CameraMapItem> cameraMapList;

    @Schema(description = "区域统计")
    @Data
    public static class AreaStat {

        @Schema(description = "区域名称", example = "园区大门")
        private String area;

        @Schema(description = "摄像头总数", example = "4")
        private Integer count;

        @Schema(description = "在线数量", example = "4")
        private Integer onlineCount;

        @Schema(description = "离线数量", example = "0")
        private Integer offlineCount;

    }

    @Schema(description = "摄像头地图点位")
    @Data
    public static class CameraMapItem {

        @Schema(description = "监控记录ID", example = "1")
        private Long id;

        @Schema(description = "摄像头名称", example = "大门摄像头")
        private String cameraName;

        @Schema(description = "经度", example = "118.675000")
        private BigDecimal lon;

        @Schema(description = "纬度", example = "24.896000")
        private BigDecimal lat;

        @Schema(description = "运行状态", example = "1")
        private String runStatus;

    }

}
