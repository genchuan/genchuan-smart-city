package cn.iocoder.yudao.module.datacenter.dal.dataobject.device.deviceinfo;

import lombok.*;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
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
 * 设备信息 DO
 *
 * @author zhucongquan
 */
@TableName("biz_device_info")
@KeySequence("biz_device_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceInfoDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
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
     * 设备类型
     */
    private String deviceType;
    /**
     * 设备型号
     */
    private String deviceModel;
    /**
     * 设备规格
     */
    private String deviceSpec;
    /**
     * 生产厂商
     */
    private String manufacturer;
    /**
     * 生产日期
     */
    private LocalDate productionDate;
    /**
     * 采购日期
     */
    private LocalDate purchaseDate;
    /**
     * 安装日期
     */
    private LocalDate installDate;
    /**
     * 质保期（月）
     */
    private Integer warrantyPeriod;
    /**
     * 设备状态
     */
    private String deviceStatus;
    /**
     * 运行状态
     */
    private String runStatus;
    /**
     * 最后在线时间
     */
    private LocalDateTime lastOnlineTime;
    /**
     * 设备大类ID
     */
    private String deviceMajorId;
    /**
     * 设备大类名称
     */
    private String deviceMajorName;
    /**
     * 设备中类ID
     */
    private String deviceMidId;
    /**
     * 设备中类名称
     */
    private String deviceMidName;
    /**
     * 设备小类ID
     */
    private String deviceMinorId;
    /**
     * 设备小类名称
     */
    private String deviceMinorName;
    /**
     * 行政区划代码
     */
    private String regionCode;
    /**
     * 行政区划名称
     */
    private String regionName;
    /**
     * 网格ID
     */
    private String gridId;
    /**
     * 网格名称
     */
    private String gridName;
    /**
     * 网格编码
     */
    private String gridCode;
    /**
     * 监测部件ID
     */
    private String monCompId;
    /**
     * 监测部件名称
     */
    private String monCompName;
    /**
     * 监测部件编码
     */
    private String monCompCode;
    /**
     * 应用场景ID
     */
    private String appSceneId;
    /**
     * 应用场景名称
     */
    private String appSceneName;
    /**
     * 应用场景编码
     */
    private String appSceneCode;
    /**
     * 资产ID
     */
    private String assetId;
    /**
     * 资产名称
     */
    private String assetName;
    /**
     * 资产编码
     */
    private String assetCode;
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
     * 安装位置描述
     */
    private String installLocation;
    /**
     * 坐标X（经度）
     */
    private BigDecimal coordX;
    /**
     * 坐标Y（纬度）
     */
    private BigDecimal coordY;
    /**
     * 高程
     */
    private BigDecimal elevation;
    /**
     * 定位精度（米）
     */
    private BigDecimal positionAccuracy;
    /**
     * IP地址
     */
    private String ipAddress;
    /**
     * MAC地址
     */
    private String macAddress;
    /**
     * 序列号
     */
    private String serialNumber;
    /**
     * 固件版本
     */
    private String firmwareVersion;
    /**
     * 软件版本
     */
    private String softwareVersion;
    /**
     * 通信协议
     */
    private String communicationProtocol;
    /**
     * 数据上报间隔（秒）
     */
    private Integer dataReportInterval;
    /**
     * 采样频率（秒）
     */
    private Integer samplingFrequency;
    /**
     * 电压等级
     */
    private String voltageLevel;
    /**
     * 供电方式
     */
    private String powerSupply;
    /**
     * 电池容量（mAh）
     */
    private Integer batteryCapacity;
    /**
     * 电池电量（%）
     */
    private Integer batteryLevel;
    /**
     * 信号强度
     */
    private Integer signalStrength;
    /**
     * 环境温度（℃）
     */
    private BigDecimal environmentTemp;
    /**
     * 环境湿度（%）
     */
    private BigDecimal environmentHumidity;
    /**
     * 维护周期（天）
     */
    private Integer maintenanceCycle;
    /**
     * 最后维护日期
     */
    private LocalDateTime lastMaintenanceDate;
    /**
     * 下次维护日期
     */
    private LocalDateTime nextMaintenanceDate;
    /**
     * 维护单位
     */
    private String maintenanceOrg;
    /**
     * 维护人
     */
    private String maintenancePerson;
    /**
     * 维护电话
     */
    private String maintenancePhone;
    /**
     * 备注
     */
    private String remark;
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