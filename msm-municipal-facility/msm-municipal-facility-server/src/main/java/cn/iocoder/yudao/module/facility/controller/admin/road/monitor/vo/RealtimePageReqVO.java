package cn.iocoder.yudao.module.facility.controller.admin.road.monitor.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;
@Schema(description = "管理后台 - 道路实时监测分页 Request VO")
@Data
//@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RealtimePageReqVO {
    //分页参数
    @Schema(description = "[开始页数]")
    private Integer pageNo;    // 第几页，从 1 开始
    @Schema(description = "[每页数量]")
    private Integer pageSize;  // 每页条数
    @Schema(hidden = true)
    private Integer offset;
    @Schema(hidden = true)
    private Integer limit;

    //路段名称
    @Schema(description = "[路段名称]")
    private String roadName;

    //设备编码
    @Schema(description = "[设备编码]")
    private String deviceCode;

    //设备在线状态
    @Schema(description = "[设备在线状态]")
    private String deviceOnlineStatus;

    //指标阈值范围
//    @Schema(description = "[指标阈值范围]")
//    private String indicatorThresholdRange;

    //上面为特殊定向的查询

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

    @Schema(description = "[预警ID列表字符串]", example = "1,2")
    private String warningIdListStr;

    @Schema(description = "[监测状态] 如:运行中/已停止", example = "运行中")
    private String monitorStatus;

    @Schema(description = "[运维员ID] 关联park_user.id", example = "30367")
    private Long staffId;

    @Schema(description = "[运维员名称] 运维员名称", example = "张三")
    private String staffName;

    @Schema(description = "[数据同步时长] 单位：秒")
    private BigDecimal syncDuration;

//    @Schema(description = "[记录时间] 监测数据记录时间")
//    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
//    private LocalDateTime[] recordTime;
//
//    @Schema(description = "[创建时间] 记录创建时间")
//    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
//    private LocalDateTime[] createTime;
    @Schema(description = "[查询起始更新时间]",example = "2026-02-28 00:00:00")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime startUpdateTime;

    @Schema(description = "[查询起始更新时间]",example = "2026-02-28 00:00:00")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endUpdateTime;



    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;
}
