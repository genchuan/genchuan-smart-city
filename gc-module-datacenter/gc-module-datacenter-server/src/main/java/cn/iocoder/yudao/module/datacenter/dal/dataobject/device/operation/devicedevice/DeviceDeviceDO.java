package cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.devicedevice;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 设备关联设备 DO
 *
 * @author zhucongquan
 */
@TableName("rel_device_device")
@KeySequence("rel_device_device_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDeviceDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 关联ID
     */
    private String deviceRelDeviceId;
    /**
     * 主设备ID
     */
    private String mainDeviceId;
    /**
     * 主设备名称
     */
    private String mainDeviceName;
    /**
     * 主设备编码
     */
    private String mainDeviceCode;
    /**
     * 从设备ID
     */
    private String slaveDeviceId;
    /**
     * 从设备名称
     */
    private String slaveDeviceName;
    /**
     * 从设备编码
     */
    private String slaveDeviceCode;
    /**
     * 关联类型
     */
    private String relType;
    /**
     * 关联时间
     */
    private LocalDateTime relTime;
    /**
     * 关联人
     */
    private String relUser;
    /**
     * 关联状态
     */
    private String relStatus;
    /**
     * 调整时间
     */
    private LocalDateTime adjustTime;
    /**
     * 调整人
     */
    private String adjustUser;
    /**
     * 调整原因
     */
    private String adjustReason;
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