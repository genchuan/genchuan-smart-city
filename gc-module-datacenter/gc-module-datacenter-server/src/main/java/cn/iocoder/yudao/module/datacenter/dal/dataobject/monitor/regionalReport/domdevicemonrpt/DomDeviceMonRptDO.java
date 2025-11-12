package cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.domdevicemonrpt;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 设备分域监测报表 DO
 *
 * @author zcq
 */
@TableName("stat_dom_device_mon_rpt")
@KeySequence("stat_dom_device_mon_rpt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DomDeviceMonRptDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 报表ID
     */
    private String rptId;
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
    private String adminCode;
    /**
     * 行政区划名称
     */
    private String adminName;
    /**
     * 设备大类ID
     */
    private String deviceMajorId;
    /**
     * 设备大类名称
     */
    private String deviceMajorName;
    /**
     * 设备小类ID
     */
    private String deviceMinorId;
    /**
     * 设备小类名称
     */
    private String deviceMinorName;
    /**
     * 分域ID
     */
    private String domId;
    /**
     * 分域名称
     */
    private String domName;
    /**
     * 设备总数
     */
    private Integer totalDeviceCount;
    /**
     * 设备在线率
     */
    private BigDecimal deviceOnlineRate;
    /**
     * 数据上传总频次
     */
    private Integer totalUploadFreq;
    /**
     * 数据上传达标率
     */
    private BigDecimal uploadQualifiedRate;
    /**
     * 设备异常次数
     */
    private Integer deviceAbnCount;
    /**
     * 固件更新设备数
     */
    private Integer firmwareUpdatedCount;
    /**
     * 固件更新率
     */
    private BigDecimal firmwareUpdateRate;
    /**
     * 报表生成时间
     */
    private LocalDateTime rptCreateTime;
    /**
     * 报表生成人
     */
    private Long rptCreateUser;
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