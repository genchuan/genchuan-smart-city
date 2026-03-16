package cn.iocoder.yudao.module.facility.controller.admin.road.roadmonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 道路监测新增/修改 Request VO")
@Data
public class RoadMonitorSaveReqVO {

//    @Schema(description = "[主键ID] 主键，道路监测记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "25706")
//    private Long id;

//    @Schema(description = "[监测编码] UUID格式", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotEmpty(message = "[监测编码] UUID格式不能为空")
    @Schema(hidden = true)
    private String monitorCode;

    @Schema(description = "[道路ID] 关联road_facility.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "[道路ID] 关联road_facility.id不能为空")
    private Long roadId;

    @Schema(description = "[设备ID] 关联park_device.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "[设备ID] 关联park_device.id不能为空")
    private Long deviceId;

    @Schema(description = "[配置ID] 关联road_config.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "[配置ID] 关联road_config.id不能为空")
    private Long configId;

    @Schema(description = "[坑洼数量] 坑洼数量",example = "100")
    private BigDecimal potholeNum;

    @Schema(description = "[裂缝长度] 裂缝长度，单位：米",example = "100")
    private BigDecimal crackLength;

    @Schema(description = "[路面温度] 路面温度，单位：摄氏度",example = "70")
    private BigDecimal roadTemp;

    @Schema(description = "[交通流量] 交通流量，单位：辆/小时",example = "100")
    private BigDecimal trafficFlow;

    @Schema(hidden = true)
//    @Schema(description = "[坑洼数量阈值快照] 采集时的坑洼数量阈值")
    private BigDecimal potholeNumThreshold;

    @Schema(hidden = true)
//    @Schema(description = "[裂缝长度阈值快照] 采集时的裂缝长度阈值")
    private BigDecimal crackLengthThreshold;

    @Schema(hidden = true)
//    @Schema(description = "[路面温度阈值快照] 采集时的路面温度阈值")
    private BigDecimal roadTempThreshold;

    @Schema(hidden = true)
//    @Schema(description = "[交通流量阈值快照] 采集时的交通流量阈值")
    private BigDecimal trafficFlowThreshold;

    @Schema(hidden = true)
//    @Schema(description = "[采集频率快照] 采集时的数据采集频率，单位：分钟")
    private BigDecimal collectFrequencySnapshot;

    @Schema(hidden = true)
//    @Schema(description = "[是否预警] 如:0-不可触发预警/1-可触发预警但还没触发/2-已预警")
    private Integer isWarning;

//    @Schema(description = "[预警ID列表字符串]", example = "1,2")
//    @ExcelProperty("[预警ID列表字符串]")
    @Schema(hidden = true)
    private String warningIdListStr;

    @Schema(hidden = true)
//    @Schema(description = "[监测状态] 如:运行中/已停止", requiredMode = Schema.RequiredMode.REQUIRED, example = "运行中")
//    @NotEmpty(message = "[监测状态] 如:运行中/已停止不能为空")
    private String monitorStatus;

    @Schema(description = "[运维员ID] 关联park_user.id", example = "1")
    private Long staffId;

    @Schema(description = "[运维员名称] 运维员名称", example = "张三")
    private String staffName;

    @Schema(hidden = true)
//    @Schema(description = "[数据同步时长] 单位：秒")
    private BigDecimal syncDuration;

    @Schema(hidden = true)
//    @Schema(description = "[记录时间] 监测数据记录时间", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "[记录时间] 监测数据记录时间不能为空")
    private LocalDateTime recordTime;

//    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
//    private String extCommon1;
//
//    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
//    private String extCommon2;
//
//    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
//    private String extCommon3;
//
//    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
//    private String extCommon4;

}
