package cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetStatReport.assetstatusrpt;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 资产状态统计 DO
 *
 * @author 亘川智城
 */
@TableName("stat_asset_status_rpt")
@KeySequence("stat_asset_status_rpt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetStatusRptDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 统计ID
     */
    private String assetStatusStatId;
    /**
     * 统计周期
     */
    private String statCycle;
    /**
     * 统计周期名称
     */
    private String statCycleName;
    /**
     * 行政区划代码
     */
    private String regionCode;
    /**
     * 行政区划名称
     */
    private String regionName;
    /**
     * 资产类型
     */
    private String assetType;
    /**
     * 资产状态
     */
    private String assetStatus;
    /**
     * 资产数量
     */
    private Integer assetCount;
    /**
     * 占比
     */
    private BigDecimal assetRatio;
    /**
     * 故障资产主要原因
     */
    private String faultReason;
    /**
     * 维护优先级
     */
    private String mntPriority;
    /**
     * 统计时间
     */
    private LocalDateTime statTime;
    /**
     * 统计人
     */
    private String statUser;
    /**
     * 报表备注
     */
    private String rptRemark;
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