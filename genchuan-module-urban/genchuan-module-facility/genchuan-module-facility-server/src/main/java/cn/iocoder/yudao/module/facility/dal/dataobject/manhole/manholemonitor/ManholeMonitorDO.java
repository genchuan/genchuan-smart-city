package cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholemonitor;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * 窨井盖监测 DO
 *
 * @author 亘川智城
 */
@TableName("manhole_monitor")
@KeySequence("manhole_monitor_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManholeMonitorDO extends BaseDO {

    /**
     * 自增主键
     */
    @TableId
    private Long id;
    /**
     * 关联窨井盖表manhole_cover的id
     */
    private Long coverId;
    /**
     * 关联设备表sys_device的id
     */
    private Long deviceId;
    /**
     * 关联用户表sys_user的id（运维员）
     */
    private Long staffId;
    /**
     * 关联开合状态表sys_open_status的id
     */
    private Long openStatusId;
    /**
     * 倾斜角度（数值，单位：度）
     */
    private BigDecimal tiltAngle;
    /**
     * 振动数据（数值，单位：m/s²）
     */
    private BigDecimal vibrationData;
    /**
     * 关联风险等级表sys_risk_level的id
     */
    private Long riskLevelId;
    /**
     * 监测状态：运行中/已停止
     */
    private String monitorStatus;
    /**
     * [通用扩展字段1] 预留
     */
    private String extCommon1;
    /**
     * [通用扩展字段2] 预留
     */
    private String extCommon2;
    /**
     * [通用扩展字段3] 预留
     */
    private String extCommon3;
    /**
     * [通用扩展字段4] 预留
     */
    private String extCommon4;

}