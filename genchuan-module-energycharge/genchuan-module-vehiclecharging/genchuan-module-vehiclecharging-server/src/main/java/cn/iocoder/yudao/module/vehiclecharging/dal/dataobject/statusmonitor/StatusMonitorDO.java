package cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.statusmonitor;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 实时监测 DO
 *
 * @author 亘川智城
 */
@TableName("status_monitor")
@KeySequence("status_monitor_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusMonitorDO extends BaseDO {

    /**
     * [主键ID] 主键ID
     */
    @TableId
    private Long id;
    /**
     * [设备编号] 设备编号
     */
    private String deviceCode;
    /**
     * [所属场站ID] 所属场站ID
     */
    private Long stationId;
    /**
     * [所属车位ID] 所属车位ID
     */
    private Long lotId;
    /**
     * [设备类型] 如：充电桩/车位
     */
    private String deviceType;
    /**
     * [电压] 单位：V
     */
    private BigDecimal voltage;
    /**
     * [电流] 单位：A
     */
    private BigDecimal current;
    /**
     * [功率] 单位：kW
     */
    private BigDecimal power;
    /**
     * [告警等级] 如：无/一般/严重
     */
    private String alarmLevel;
    /**
     * [监测状态] 如：正常/异常/处置中/已恢复
     */
    private String monitorStatus;
    /**
     * [处置人员] 处置人员
     */
    private String disposeUser;
    /**
     * [处置措施] 处置措施
     */
    private String disposeMeasure;
    /**
     * [处置时间] 处置时间
     */
    private LocalDateTime disposeTime;
    /**
     * [监测时间] 监测时间
     */
    private LocalDateTime monitorTime;
    /**
     * [备注] 备注
     */
    private String remark;
    /**
     * [备用字段1] 备用字段1
     */
    private String reserve1;
    /**
     * [备用字段2] 备用字段2
     */
    private String reserve2;


}
