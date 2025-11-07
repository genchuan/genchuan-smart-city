package cn.iocoder.yudao.module.smartcity.dal.dataobject.monitoringdevice;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 监测设备 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_monitoring_device")
@KeySequence("smartcity_monitoring_device_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonitoringDeviceDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 设备编号
     */
    private String deviceNumber;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 设备类型
     */
    private String deviceType;
    /**
     * 生产厂家
     */
    private String manufacturer;
    /**
     * 安装日期
     */
    private LocalDateTime installationDate;
    /**
     * 维护记录
     */
    private String maintenanceRecord;

}