package cn.iocoder.yudao.module.inspectop.dal.dataobject.devicemonitor.bikechargemonitor;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 两轮充电监测 DO
 *
 * @author zhucongquan
 */
@TableName("bike_charge_monitor")
@KeySequence("bike_charge_monitor_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BikeChargeMonitorDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 设备ID
     */
    private Long deviceId;
    /**
     * 场站ID
     */
    private Long stationId;
    /**
     * 监测时间
     */
    private LocalDateTime monitorTime;
    /**
     * 监测状态
     */
    private String monitorStatus;
    /**
     * 告警状态
     */
    private String alarmStatus;
    /**
     * 告警时间
     */
    private LocalDateTime alarmTime;
    /**
     * 告警备注
     */
    private String alarmRemark;
    /**
     * 处理状态
     */
    private String processStatus;
    /**
     * 经度
     */
    private BigDecimal longitude;
    /**
     * 纬度
     */
    private BigDecimal latitude;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}