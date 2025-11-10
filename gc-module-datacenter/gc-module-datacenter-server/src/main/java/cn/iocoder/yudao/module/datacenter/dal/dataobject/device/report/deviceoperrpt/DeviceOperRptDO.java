package cn.iocoder.yudao.module.datacenter.dal.dataobject.device.report.deviceoperrpt;

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
 * 设备运行状态统计 DO
 *
 * @author zhucongquan
 */
@TableName("stat_device_oper_rpt")
@KeySequence("stat_device_oper_rpt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceOperRptDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 统计ID
     */
    private String operStatId;
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
     * 设备总数量(台)
     */
    private Integer totalDeviceCount;
    /**
     * 平均在线设备数(台)
     */
    private Integer avgOnlineCount;
    /**
     * 设备在线率(%)
     */
    private BigDecimal deviceOnlineRate;
    /**
     * 总离线时长(分钟)
     */
    private Integer totalOfflineEndure;
    /**
     * 平均离线时长(分钟)
     */
    private BigDecimal avgOfflineEndure;
    /**
     * 一般预警数(条)
     */
    private Integer generalAlertCount;
    /**
     * 重要预警数(条)
     */
    private Integer importantAlertCount;
    /**
     * 紧急预警数(条)
     */
    private Integer urgentAlertCount;
    /**
     * 已处理预警数(条)
     */
    private Integer handledAlertCount;
    /**
     * 预警处置率(%)
     */
    private BigDecimal alertHandleRate;
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