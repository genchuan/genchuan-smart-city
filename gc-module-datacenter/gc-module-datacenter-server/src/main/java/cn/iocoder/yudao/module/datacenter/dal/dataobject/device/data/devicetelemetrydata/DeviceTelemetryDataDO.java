package cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.devicetelemetrydata;

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
 * 设备遥测数据 DO
 *
 * @author zhucongquan
 */
@TableName("biz_device_telemetry_data")
@KeySequence("biz_device_telemetry_data_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceTelemetryDataDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 遥测数据ID
     */
    private String telemetryDataId;
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
     * 遥测项名称
     */
    private String telemetryItemName;
    /**
     * 遥测项代码
     */
    private String telemetryItemCode;
    /**
     * 遥测值
     */
    private String telemetryValue;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 采集时间
     */
    private LocalDateTime collectTime;
    /**
     * 数据来源
     */
    private String dataSource;
    /**
     * 存储周期(天)
     */
    private Integer storageCycle;
    /**
     * 过期状态
     */
    private String expireStatus;
    /**
     * 创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 更新时间
     */
    private LocalDateTime updateTimeSys;
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