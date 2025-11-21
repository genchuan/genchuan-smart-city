package cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnmonevt;

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
 * 按监测事件预警告警统计 DO
 *
 * @author zcq
 */
@TableName("stat_early_warn_mon_evt")
@KeySequence("stat_early_warn_mon_evt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarlyWarnMonEvtDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 统计ID
     */
    private String warnMonEvtStatId;
    /**
     * 统计周期
     */
    private String statCycle;
    /**
     * 事件大类ID
     */
    private String evtMajorId;
    /**
     * 事件大类名称
     */
    private String evtMajorName;
    /**
     * 事件小类ID
     */
    private String evtMinorId;
    /**
     * 事件小类名称
     */
    private String evtMinorName;
    /**
     * 预警总数
     */
    private Integer totalWarnCount;
    /**
     * 平均响应时间
     */
    private BigDecimal avgRespTime;
    /**
     * 涉及区域数
     */
    private Integer regionCount;
    /**
     * 已办结数
     */
    private Integer completedCount;
    /**
     * 处置率
     */
    private BigDecimal handleRate;
    /**
     * 统计人
     */
    private String statUserId;
    /**
     * 统计时间
     */
    private LocalDateTime statTime;
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