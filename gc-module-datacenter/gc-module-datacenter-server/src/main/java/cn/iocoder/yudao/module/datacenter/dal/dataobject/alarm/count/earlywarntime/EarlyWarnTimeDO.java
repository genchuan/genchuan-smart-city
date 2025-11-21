package cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarntime;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 预警告警时间维度统计 DO
 *
 * @author zcq
 */
@TableName("stat_early_warn_time")
@KeySequence("stat_early_warn_time_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarlyWarnTimeDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 统计ID
     */
    private String timeStatId;
    /**
     * 统计维度
     */
    private String timeDimension;
    /**
     * 统计周期值
     */
    private String statCycleValue;
    /**
     * 统计周期名称
     */
    private String statCycleName;
    /**
     * 预警总数
     */
    private Integer totalWarnCount;
    /**
     * 主要预警类型
     */
    private String mainWarnType;
    /**
     * 主要预警类型数量
     */
    private Integer mainTypeCount;
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