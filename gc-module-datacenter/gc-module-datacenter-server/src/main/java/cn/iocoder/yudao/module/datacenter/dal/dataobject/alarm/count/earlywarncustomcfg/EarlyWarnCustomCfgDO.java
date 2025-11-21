package cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarncustomcfg;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 预警告警自定义统计配置 DO
 *
 * @author zcq
 */
@TableName("sys_early_warn_custom_cfg")
@KeySequence("sys_early_warn_custom_cfg_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EarlyWarnCustomCfgDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 配置ID
     */
    private String customStatCfgId;
    /**
     * 配置名称
     */
    private String cfgName;
    /**
     * 统计周期
     */
    private String statCycle;
    /**
     * 统计维度
     */
    private String statDimensions;
    /**
     * 筛选条件
     */
    private String filterConditions;
    /**
     * 分组维度
     */
    private String groupDimensions;
    /**
     * 统计指标
     */
    private String statIndicators;
    /**
     * 创建人ID
     */
    private String createUserId;
    /**
     * 创建人姓名
     */
    private String createUserName;
    /**
     * 是否常用
     */
    private String isFrequentlyUsed;
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