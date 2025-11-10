package cn.iocoder.yudao.module.datacenter.dal.dataobject.device.status.deviceofflinealert;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 设备离线预警 DO
 *
 * @author zhucongquan
 */
@TableName("sys_device_offline_alert")
@KeySequence("sys_device_offline_alert_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceOfflineAlertDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 预警ID
     */
    private String offlineAlertId;
    /**
     * 设备ID
     */
    private String deviceId;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 设备编码
     */
    private String deviceCode;
    /**
     * 离线开始时间
     */
    private LocalDateTime offlineStartTime;
    /**
     * 离线时长(分钟)
     */
    private Integer offlineEndure;
    /**
     * 预警级别
     */
    private String alertLevel;
    /**
     * 预警接收人ID
     */
    private String alertUserId;
    /**
     * 预警接收人姓名
     */
    private String alertUserName;
    /**
     * 预警推送时间
     */
    private LocalDateTime alertPushTime;
    /**
     * 预警状态
     */
    private String alertStatus;
    /**
     * 处理人ID
     */
    private String handleUserId;
    /**
     * 处理人姓名
     */
    private String handleUserName;
    /**
     * 处理时间
     */
    private LocalDateTime handleTime;
    /**
     * 处理结果
     */
    private String handleResult;
    /**
     * 所属行政区划代码
     */
    private String regionCode;
    /**
     * 所属行政区划名称
     */
    private String regionName;
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