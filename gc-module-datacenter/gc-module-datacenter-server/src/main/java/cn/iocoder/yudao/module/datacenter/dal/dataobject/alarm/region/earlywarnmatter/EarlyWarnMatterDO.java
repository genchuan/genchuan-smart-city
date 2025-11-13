package cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnmatter;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 按管理事项预警告警统计 DO
 *
 * @author zcq
 */
@TableName("stat_early_warn_matter")
@KeySequence("stat_early_warn_matter_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarlyWarnMatterDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 统计ID
     */
    private String warnMngMatterStatId;
    /**
     * 统计周期
     */
    private String statCycle;
    /**
     * 事项大类ID
     */
    private String matterMajorId;
    /**
     * 事项大类名称
     */
    private String matterMajorName;
    /**
     * 事项小类ID
     */
    private String matterMinorId;
    /**
     * 事项小类名称
     */
    private String matterMinorName;
    /**
     * 预警总数
     */
    private Integer totalWarnCount;
    /**
     * 已办结数
     */
    private Integer completedCount;
    /**
     * 超时未处置数
     */
    private Integer overtimeCount;
    /**
     * 处置率
     */
    private BigDecimal handleRate;
    /**
     * 主要处置部门代码
     */
    private String mainDeptCode;
    /**
     * 主要处置部门名称
     */
    private String mainDeptName;
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