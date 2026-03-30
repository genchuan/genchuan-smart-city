package cn.iocoder.yudao.module.facility.controller.admin.road.roadmonitor.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 道路监测 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RealtimePageRespVO {

    @Schema(description = "[主键ID] 主键，道路监测记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "25706")
    @ExcelProperty("[主键ID] 主键，道路监测记录唯一标识")
    private Long id;



    @Schema(description = "[监测编码] UUID格式", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[监测编码] UUID格式")
    private String monitorCode;

    @Schema(description = "[道路ID] 关联road_facility.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "5922")
    @ExcelProperty("[道路ID] 关联road_facility.id")
    private Long roadId;

    @Schema(description = "[设备ID] 关联park_device.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "11230")
    @ExcelProperty("[设备ID] 关联park_device.id")
    private Long deviceId;

    @Schema(description = "[配置ID] 关联road_config.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "22081")
    @ExcelProperty("[配置ID] 关联road_config.id")
    private Long configId;




    //路段名称
    @Schema(description = "[路段名称]", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[路段名称]")
    private String roadName;

    //设备编码
    @Schema(description = "[设备编码]", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[设备编码]")
    private String deviceCode;

    //设备在线状态
    @Schema(description = "[设备在线状态]", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[设备在线状态]")
    private String deviceOnlineStatus;

    //指标阈值范围
    @Schema(description = "[指标阈值范围]", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[指标阈值范围]")
    private String indicatorThresholdRange;


    @Schema(description = "[坑洼数量] 坑洼数量")
    @ExcelProperty("[坑洼数量] 坑洼数量")
    private BigDecimal potholeNum;

    @Schema(description = "[裂缝长度] 裂缝长度，单位：米")
    @ExcelProperty("[裂缝长度] 裂缝长度，单位：米")
    private BigDecimal crackLength;

    @Schema(description = "[路面温度] 路面温度，单位：摄氏度")
    @ExcelProperty("[路面温度] 路面温度，单位：摄氏度")
    private BigDecimal roadTemp;

    @Schema(description = "[交通流量] 交通流量，单位：辆/小时")
    @ExcelProperty("[交通流量] 交通流量，单位：辆/小时")
    private BigDecimal trafficFlow;

    @Schema(description = "[坑洼数量阈值快照] 采集时的坑洼数量阈值")
    @ExcelProperty("[坑洼数量阈值快照] 采集时的坑洼数量阈值")
    private BigDecimal potholeNumThreshold;

    @Schema(description = "[裂缝长度阈值快照] 采集时的裂缝长度阈值")
    @ExcelProperty("[裂缝长度阈值快照] 采集时的裂缝长度阈值")
    private BigDecimal crackLengthThreshold;

    @Schema(description = "[路面温度阈值快照] 采集时的路面温度阈值")
    @ExcelProperty("[路面温度阈值快照] 采集时的路面温度阈值")
    private BigDecimal roadTempThreshold;

    @Schema(description = "[交通流量阈值快照] 采集时的交通流量阈值")
    @ExcelProperty("[交通流量阈值快照] 采集时的交通流量阈值")
    private BigDecimal trafficFlowThreshold;

    @Schema(description = "[采集频率快照] 采集时的数据采集频率，单位：分钟")
    @ExcelProperty("[采集频率快照] 采集时的数据采集频率，单位：分钟")
    private BigDecimal collectFrequencySnapshot;

    @Schema(description = "[是否预警] 如:0-不可触发预警/1-可触发预警但还没触发/2-已预警")
    @ExcelProperty("[是否预警] 如:0-否/1-是")
    private Integer isWarning;

    @Schema(description = "[预警ID列表字符串] 关联预警表ID列表字符串", example = "1,2")
    @ExcelProperty("[预警ID列表字符串]")
    private String warningIdListStr;

    @Schema(description = "[监测状态] 如:运行中/已停止", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("[监测状态] 如:运行中/已停止")
    private String monitorStatus;

    @Schema(description = "[运维员ID] 关联park_user.id", example = "30367")
    @ExcelProperty("[运维员ID] 关联park_user.id")
    private Long staffId;

    @Schema(description = "[运维员名称] 运维员名称", example = "张三")
    @ExcelProperty("[运维员名称] 运维员名称")
    private String staffName;

    @Schema(description = "[数据同步时长] 单位：秒")
    @ExcelProperty("[数据同步时长] 单位：秒")
    private BigDecimal syncDuration;

    @Schema(description = "[记录时间] 监测数据记录时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[记录时间] 监测数据记录时间")
    private LocalDateTime recordTime;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[更新时间] 记录更新时间")
    @ExcelProperty("[更新时间] 记录更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    @ExcelProperty("[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    @ExcelProperty("[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    @ExcelProperty("[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    @ExcelProperty("[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}
