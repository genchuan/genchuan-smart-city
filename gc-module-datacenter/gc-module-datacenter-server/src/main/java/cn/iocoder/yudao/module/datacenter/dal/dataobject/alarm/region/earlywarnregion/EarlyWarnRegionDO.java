package cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnregion;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 按行政区划预警告警统计 DO
 *
 * @author zcq
 */
@TableName("stat_early_warn_region")
@KeySequence("stat_early_warn_region_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarlyWarnRegionDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 统计ID
     */
    private String warnRegionStatId;
    /**
     * 统计周期
     */
    private String statCycle;
    /**
     * 行政区划代码
     */
    private String regionCode;
    /**
     * 行政区划名称
     */
    private String regionName;
    /**
     * 行政区划级别
     */
    private String regionLevel;
    /**
     * 预警总数
     */
    private Integer totalWarnCount;
    /**
     * 一般预警数
     */
    private Integer normalLevelCount;
    /**
     * 较重预警数
     */
    private Integer heavyLevelCount;
    /**
     * 严重预警数
     */
    private Integer seriousLevelCount;
    /**
     * 特别严重预警数
     */
    private Integer criticalLevelCount;
    /**
     * 已办结数
     */
    private Integer completedCount;
    /**
     * 待处置数
     */
    private Integer pendCount;
    /**
     * 处置率(%)
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