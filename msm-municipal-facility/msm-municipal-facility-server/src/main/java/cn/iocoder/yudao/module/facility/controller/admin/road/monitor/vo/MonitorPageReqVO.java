package cn.iocoder.yudao.module.facility.controller.admin.road.monitor.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 道路监测分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MonitorPageReqVO extends PageParam {

    @Schema(description = "[监测编码] UUID格式")
    private String monitorCode;

    @Schema(description = "[道路ID] 关联road_facility.id", example = "5922")
    private Long roadId;

    @Schema(description = "[设备ID] 关联park_device.id", example = "11230")
    private Long deviceId;

    @Schema(description = "[配置ID] 关联road_config.id", example = "22081")
    private Long configId;

    @Schema(description = "[坑洼数量] 坑洼数量")
    private BigDecimal potholeNum;

    @Schema(description = "[裂缝长度] 裂缝长度，单位：米")
    private BigDecimal crackLength;

    @Schema(description = "[路面温度] 路面温度，单位：摄氏度")
    private BigDecimal roadTemp;

    @Schema(description = "[交通流量] 交通流量，单位：辆/小时")
    private BigDecimal trafficFlow;

    @Schema(description = "[坑洼数量阈值快照] 采集时的坑洼数量阈值")
    private BigDecimal potholeNumThreshold;

    @Schema(description = "[裂缝长度阈值快照] 采集时的裂缝长度阈值")
    private BigDecimal crackLengthThreshold;

    @Schema(description = "[路面温度阈值快照] 采集时的路面温度阈值")
    private BigDecimal roadTempThreshold;

    @Schema(description = "[交通流量阈值快照] 采集时的交通流量阈值")
    private BigDecimal trafficFlowThreshold;

    @Schema(description = "[采集频率快照] 采集时的数据采集频率，单位：分钟")
    private BigDecimal collectFrequencySnapshot;

    @Schema(description = "[是否预警] 如:0-否/1-是")
    private Integer isWarning;

    @Schema(description = "[预警ID] 关联预警表ID", example = "30795")
    private Long warningId;

    @Schema(description = "[监测状态] 如:运行中/已停止", example = "2")
    private String monitorStatus;

    @Schema(description = "[运维员ID] 关联park_user.id", example = "30367")
    private Long staffId;

    @Schema(description = "[运维员名称] 运维员名称", example = "张三")
    private String staffName;

    @Schema(description = "[数据同步时长] 单位：秒")
    private BigDecimal syncDuration;

    @Schema(description = "[记录时间] 监测数据记录时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] recordTime;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}
