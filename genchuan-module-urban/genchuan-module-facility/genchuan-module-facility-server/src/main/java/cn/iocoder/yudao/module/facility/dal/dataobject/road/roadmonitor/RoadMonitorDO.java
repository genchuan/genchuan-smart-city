package cn.iocoder.yudao.module.facility.dal.dataobject.road.roadmonitor;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 道路监测 DO
 *
 * @author 亘川智城
 */
@TableName("road_monitor")
@KeySequence("road_monitor_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoadMonitorDO extends BaseDO {

    /**
     * [主键ID] 主键，道路监测记录唯一标识
     */
    @TableId
    private Long id;
    /**
     * [监测编码] UUID格式
     */
    private String monitorCode;
    /**
     * [道路ID] 关联road_facility.id
     */
    private Long roadId;
    /**
     * [设备ID] 关联park_device.id
     */
    private Long deviceId;
    /**
     * [配置ID] 关联road_config.id
     */
    private Long configId;
    /**
     * [坑洼数量] 坑洼数量
     */
    private BigDecimal potholeNum;
    /**
     * [裂缝长度] 裂缝长度，单位：米
     */
    private BigDecimal crackLength;
    /**
     * [路面温度] 路面温度，单位：摄氏度
     */
    private BigDecimal roadTemp;
    /**
     * [交通流量] 交通流量，单位：辆/小时
     */
    private BigDecimal trafficFlow;
    /**
     * [坑洼数量阈值快照] 采集时的坑洼数量阈值
     */
    private BigDecimal potholeNumThreshold;
    /**
     * [裂缝长度阈值快照] 采集时的裂缝长度阈值
     */
    private BigDecimal crackLengthThreshold;
    /**
     * [路面温度阈值快照] 采集时的路面温度阈值
     */
    private BigDecimal roadTempThreshold;
    /**
     * [交通流量阈值快照] 采集时的交通流量阈值
     */
    private BigDecimal trafficFlowThreshold;
    /**
     * [采集频率快照] 采集时的数据采集频率，单位：分钟
     */
    private BigDecimal collectFrequencySnapshot;
    /**
     * [是否预警] 如:0-不可触发预警/1-可触发预警但还没触发/2-已预警
     */
    private Integer isWarning;

//    预警ID列表字符串
    private String warningIdListStr;
    /**
     * [监测状态] 如:运行中/已停止
     */
    private String monitorStatus;
    /**
     * [运维员ID] 关联park_user.id
     */
    private Long staffId;
    /**
     * [运维员名称] 运维员名称
     */
    private String staffName;
    /**
     * [数据同步时长] 单位：秒
     */
    private BigDecimal syncDuration;
    /**
     * [记录时间] 监测数据记录时间
     */
    private LocalDateTime recordTime;
    /**
     * [通用扩展字段1] 通用扩展字段1
     */
    private String extCommon1;
    /**
     * [通用扩展字段2] 通用扩展字段2
     */
    private String extCommon2;
    /**
     * [通用扩展字段3] 通用扩展字段3
     */
    private String extCommon3;
    /**
     * [通用扩展字段4] 通用扩展字段4
     */
    private String extCommon4;

}
