package cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnasset;

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
 * 按资产分域预警告警统计 DO
 *
 * @author zcq
 */
@TableName("stat_early_warn_asset")
@KeySequence("stat_early_warn_asset_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarlyWarnAssetDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 统计ID
     */
    private String warnAssetStatId;
    /**
     * 统计周期
     */
    private String statCycle;
    /**
     * 资产大类ID
     */
    private String assetMajorId;
    /**
     * 资产大类名称
     */
    private String assetMajorName;
    /**
     * 资产小类ID
     */
    private String assetMinorId;
    /**
     * 资产小类名称
     */
    private String assetMinorName;
    /**
     * 预警总数
     */
    private Integer totalWarnCount;
    /**
     * 主要故障类型
     */
    private String mainFaultType;
    /**
     * 涉及资产数
     */
    private Integer assetCount;
    /**
     * 维修总成本
     */
    private BigDecimal mntCost;
    /**
     * 已修复资产数
     */
    private Integer repairedAssetCount;
    /**
     * 修复率
     */
    private BigDecimal repairRate;
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