package cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnmoncomp;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 按监测部件预警告警统计 DO
 *
 * @author zcq
 */
@TableName("stat_early_warn_mon_comp")
@KeySequence("stat_early_warn_mon_comp_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarlyWarnMonCompDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 统计ID
     */
    private String warnMonCompStatId;
    /**
     * 统计周期
     */
    private String statCycle;
    /**
     * 部件大类ID
     */
    private String monMajorId;
    /**
     * 部件大类名称
     */
    private String monMajorName;
    /**
     * 部件小类ID
     */
    private String monMinorId;
    /**
     * 部件小类名称
     */
    private String monMinorName;
    /**
     * 预警总数
     */
    private Integer totalWarnCount;
    /**
     * 主要触发原因
     */
    private String mainTriggerReason;
    /**
     * 涉及设备数
     */
    private Integer deviceCount;
    /**
     * 已恢复数
     */
    private Integer recoveredCount;
    /**
     * 恢复率
     */
    private BigDecimal recoveryRate;
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