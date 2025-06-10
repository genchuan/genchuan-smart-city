package cn.iocoder.yudao.module.smartcity.controller.admin.floodsafety.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 内涝安全新增/修改 Request VO")
@Data
public class FloodSafetySaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "24169")
    private Long id;

    @Schema(description = "监测点名称", example = "李四")
    private String monitoringPointName;

    @Schema(description = "监测点位置")
    private String locationOfMonitoringPoints;

    @Schema(description = "积水深度")
    private String depthOfAccumulatedWater;

    @Schema(description = "积水面积")
    private String catchmentArea;

    @Schema(description = "水流速度")
    private String waterFlowVelocity;

    @Schema(description = "水位变化趋势")
    private String trendOfWaterLevelChanges;

    @Schema(description = "降雨量")
    private String rainfall;

    @Schema(description = "降雨强度")
    private String rainfallIntensity;

    @Schema(description = "管道堵塞情况")
    private String pipelineBlockageSituation;

    @Schema(description = "泵站运行状态", example = "1")
    private String pumpStationOperationStatus;

    @Schema(description = "设备故障信息")
    private String equipmentFaultInformation;

    @Schema(description = "预警级别")
    private String warningLevel;

    @Schema(description = "预警时间")
    private LocalDateTime warningTime;

}