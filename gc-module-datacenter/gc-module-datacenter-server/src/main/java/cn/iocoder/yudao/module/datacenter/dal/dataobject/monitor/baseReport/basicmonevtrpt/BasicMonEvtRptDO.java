package cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.baseReport.basicmonevtrpt;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 基础监测事件报 DO
 *
 * @author zhucongquan
 */
@TableName("stat_basic_mon_evt_rpt")
@KeySequence("stat_basic_mon_evt_rpt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicMonEvtRptDO extends BaseDO {

    /**
     * 主键ID
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
     * 事件类型
     */
    private String evtType;
    /**
     * 事件总数
     */
    private Integer totalEvtCount;
    /**
     * 待处置事件数
     */
    private Integer pendEvtCount;
    /**
     * 处置中事件数
     */
    private Integer handlEvtCount;
    /**
     * 已办结事件数
     */
    private Integer completedEvtCount;
    /**
     * 事件办结率(%)
     */
    private BigDecimal evtCompleteRate;
    /**
     * 平均处置时长(分钟)
     */
    private BigDecimal avgDealEndure;
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