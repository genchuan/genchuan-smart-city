package cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarngrid;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 按网格分域预警告警统计 DO
 *
 * @author zcq
 */
@TableName("stat_early_warn_grid")
@KeySequence("stat_early_warn_grid_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarlyWarnGridDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 统计ID
     */
    private String warnGridStatId;
    /**
     * 统计周期
     */
    private String statCycle;
    /**
     * 网格类型
     */
    private String gridType;
    /**
     * 网格ID
     */
    private String gridId;
    /**
     * 网格编码
     */
    private String gridCode;
    /**
     * 网格名称
     */
    private String gridName;
    /**
     * 所属街道代码
     */
    private String streetCode;
    /**
     * 所属街道名称
     */
    private String streetName;
    /**
     * 预警总数
     */
    private Integer totalWarnCount;
    /**
     * 已办结数
     */
    private Integer completedCount;
    /**
     * 待处置数
     */
    private Integer pendCount;
    /**
     * 主要预警类型
     */
    private String mainWarnType;
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