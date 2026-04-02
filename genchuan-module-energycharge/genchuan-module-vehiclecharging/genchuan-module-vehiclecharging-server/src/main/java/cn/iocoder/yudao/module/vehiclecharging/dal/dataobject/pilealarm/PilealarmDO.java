package cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.pilealarm;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 充电桩告警 DO
 *
 * @author 亘川智城
 */
@TableName("pilealarm")
@KeySequence("pilealarm_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PilealarmDO extends BaseDO {

    /**
     * 主键ID（UUID）
     */
    @TableId(type = IdType.INPUT)
    private String id;
    /**
     * 告警编码（唯一）
     */
    private String alarmCode;
    /**
     * 充电桩ID（关联charging_pile）
     */
    private String pileId;
    /**
     * 所属场站ID（关联charging_station）
     */
    private String stationId;
    /**
     * 故障类型：硬件/软件/网络/计费 字典类型：pilealarm_fault_type
     */
    private String faultType;
    /**
     * 故障描述
     */
    private String faultDesc;
    /**
     * 告警等级：一级/二级/三级 字典类型：pilealarm_alarm_level
     */
    private String alarmLevel;
    /**
     * 告警时间
     */
    private LocalDateTime alarmTime;
    /**
     * 处理人ID（关联system_user）
     */
    private Long handlerId;
    /**
     * 处理时长（小时）
     */
    private BigDecimal handleHour;
    /**
     * 处理结果
     */
    private String handleResult;
    /**
     * 告警状态：未确认/已确认/处理中 字典类型：pilealarm_status
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;
    /**
     * 备用字段3
     */
    private String reserve3;
    /**
     * 创建人（关联system_user）
     */
    private Long createBy;
    /**
     * 更新人
     */
    private Long updateBy;


}