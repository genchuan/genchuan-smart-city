package cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarndevice;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 按设备分域预警告警统计 DO
 *
 * @author zcq
 */
@TableName("stat_early_warn_device")
@KeySequence("stat_early_warn_device_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarlyWarnDeviceDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 统计ID
     */
    private String warnDeviceStatId;
    /**
     * 统计周期
     */
    private String statCycle;
    /**
     * 设备大类ID
     */
    private String deviceMajorId;
    /**
     * 设备大类名称
     */
    private String deviceMajorName;
    /**
     * 设备型号ID
     */
    private String deviceModelId;
    /**
     * 设备型号名称
     */
    private String deviceModelName;
    /**
     * 预警总数
     */
    private Integer totalWarnCount;
    /**
     * 主要故障类型
     */
    private String mainFaultType;
    /**
     * 涉及设备数
     */
    private Integer deviceCount;
    /**
     * 在线修复数
     */
    private Integer onlineRepairCount;
    /**
     * 在线修复率
     */
    private BigDecimal onlineRepairRate;
    /**
     * 统计人
     */
    private String statUserId;
    /**
     * 统计时间
     */
    private LocalDateTime statTime;
    /**
     * 分类扩展字段1
     */
    private String extCat1;
    /**
     * 分类扩展字段2
     */
    private String extCat2;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;

}