package cn.iocoder.yudao.module.smartcity.controller.admin.floodsafety.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 内涝安全 Response VO")
@Data
@ExcelIgnoreUnannotated
public class FloodSafetyRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "24169")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "监测点名称", example = "李四")
    @ExcelProperty("监测点名称")
    private String monitoringPointName;

    @Schema(description = "监测点位置")
    @ExcelProperty("监测点位置")
    private String locationOfMonitoringPoints;

    @Schema(description = "积水深度")
    @ExcelProperty("积水深度")
    private String depthOfAccumulatedWater;

    @Schema(description = "积水面积")
    @ExcelProperty("积水面积")
    private String catchmentArea;

    @Schema(description = "水流速度")
    @ExcelProperty("水流速度")
    private String waterFlowVelocity;

    @Schema(description = "水位变化趋势")
    @ExcelProperty("水位变化趋势")
    private String trendOfWaterLevelChanges;

    @Schema(description = "降雨量")
    @ExcelProperty("降雨量")
    private String rainfall;

    @Schema(description = "降雨强度")
    @ExcelProperty("降雨强度")
    private String rainfallIntensity;

    @Schema(description = "管道堵塞情况")
    @ExcelProperty("管道堵塞情况")
    private String pipelineBlockageSituation;

    @Schema(description = "泵站运行状态", example = "1")
    @ExcelProperty("泵站运行状态")
    private String pumpStationOperationStatus;

    @Schema(description = "设备故障信息")
    @ExcelProperty("设备故障信息")
    private String equipmentFaultInformation;

    @Schema(description = "预警级别")
    @ExcelProperty("预警级别")
    private String warningLevel;

    @Schema(description = "预警时间")
    @ExcelProperty("预警时间")
    private LocalDateTime warningTime;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}