package cn.iocoder.yudao.module.datacenter.dal.dataobject.device.status.devicerealtimestatus;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 设备实时状态 DO
 *
 * @author zhucongquan
 */
@TableName("sys_device_real_time_status")
@KeySequence("sys_device_real_time_status_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceRealTimeStatusDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 状态ID
     */
    private String realTimeStatusId;
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
     * 运行状态
     */
    private String runStatus;
    /**
     * 最近上报时间
     */
    private LocalDateTime lastRptTime;
    /**
     * 实时参数1
     */
    private String realTimeParam1;
    /**
     * 实时参数2
     */
    private String realTimeParam2;
    /**
     * 状态更新时间
     */
    private LocalDateTime statusUpdateTime;
    /**
     * 所属行政区划代码
     */
    private String regionCode;
    /**
     * 所属行政区划名称
     */
    private String regionName;
    /**
     * 所属网格ID
     */
    private String gridId;
    /**
     * 所属网格名称
     */
    private String gridName;
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