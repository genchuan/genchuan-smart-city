package cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnmngcomp;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 按管理部件预警告警统计 DO
 *
 * @author zcq
 */
@TableName("stat_early_warn_mng_comp")
@KeySequence("stat_early_warn_mng_comp_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarlyWarnMngCompDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 统计ID
     */
    private String warnMngCompStatId;
    /**
     * 统计周期
     */
    private String statCycle;
    /**
     * 部件大类ID
     */
    private String compMajorId;
    /**
     * 部件大类名称
     */
    private String compMajorName;
    /**
     * 部件小类ID
     */
    private String compMinorId;
    /**
     * 部件小类名称
     */
    private String compMinorName;
    /**
     * 预警总数
     */
    private Integer totalWarnCount;
    /**
     * 主要故障类型
     */
    private String mainFaultType;
    /**
     * 主要分布区域代码
     */
    private String mainRegionCode;
    /**
     * 主要分布区域名称
     */
    private String mainRegionName;
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