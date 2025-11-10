package cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.devicespatialdata;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
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
 * 设备空间数据 DO
 *
 * @author zhucongquan
 */
@TableName("sys_device_spatial_data")
@KeySequence("sys_device_spatial_data_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceSpatialDataDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 空间数据ID
     */
    private String deviceSpatialId;
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
     * 坐标系类型
     */
    private String coordSystem;
    /**
     * 坐标X(度)
     */
    private BigDecimal coordX;
    /**
     * 坐标Y(度)
     */
    private BigDecimal coordY;
    /**
     * 高程(米)
     */
    private BigDecimal elevation;
    /**
     * 定位精度(米)
     */
    private BigDecimal positionAccuracy;
    /**
     * 空间数据格式
     */
    private String spatialDataFormat;
    /**
     * 数据来源
     */
    private String dataSource;
    /**
     * 录入时间
     */
    private LocalDateTime inputTime;
    /**
     * 录入人
     */
    private String inputUser;
    /**
     * 更新时间
     */
    private LocalDateTime updateTimeSys;
    /**
     * 更新人
     */
    private String updateUser;
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