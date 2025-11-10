package cn.iocoder.yudao.module.datacenter.dal.dataobject.device.report.devicecatrpt;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 设备分类统计 DO
 *
 * @author zhucongquan
 */
@TableName("stat_device_cat_rpt")
@KeySequence("stat_device_cat_rpt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceCatRptDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 统计ID
     */
    private String catStatId;
    /**
     * 统计周期
     */
    private String statCycle;
    /**
     * 统计周期名称
     */
    private String statCycleName;
    /**
     * 行政区划代码
     */
    private String regionCode;
    /**
     * 行政区划名称
     */
    private String regionName;
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
     * 设备总数(台)
     */
    private Integer totalDeviceCount;
    /**
     * 在线设备数(台)
     */
    private Integer onlineDeviceCount;
    /**
     * 离线设备数(台)
     */
    private Integer offlineDeviceCount;
    /**
     * 故障设备数(台)
     */
    private Integer faultDeviceCount;
    /**
     * 新增设备数(台)
     */
    private Integer newDeviceCount;
    /**
     * 报废设备数(台)
     */
    private Integer scrappedDeviceCount;
    /**
     * 统计时间
     */
    private LocalDateTime statTime;
    /**
     * 统计人
     */
    private String statUser;
    /**
     * 报表备注
     */
    private String rptRemark;
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