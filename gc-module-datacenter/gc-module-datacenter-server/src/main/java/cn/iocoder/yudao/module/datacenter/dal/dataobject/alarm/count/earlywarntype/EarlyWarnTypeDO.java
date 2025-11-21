package cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarntype;

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
 * 预警告警类型维度统计 DO
 *
 * @author zcq
 */
@TableName("stat_early_warn_type")
@KeySequence("stat_early_warn_type_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarlyWarnTypeDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 统计ID
     */
    private String typeStatId;
    /**
     * 统计周期
     */
    private String statCycle;
    /**
     * 预警类型
     */
    private String warnType;
    /**
     * 预警类型编码
     */
    private String warnTypeCode;
    /**
     * 预警总数
     */
    private Integer totalWarnCount;
    /**
     * 占比
     */
    private BigDecimal proportion;
    /**
     * 已办结数
     */
    private Integer completedCount;
    /**
     * 处置率
     */
    private BigDecimal handleRate;
    /**
     * 主要分布区域名称
     */
    private String mainRegionName;
    /**
     * 主要分布区域数量
     */
    private Integer mainRegionCount;
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