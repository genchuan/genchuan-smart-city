package cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.domassetmonrpt;

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
 * 资产分域监测报表 DO
 *
 * @author zcq
 */
@TableName("stat_dom_asset_mon_rpt")
@KeySequence("stat_dom_asset_mon_rpt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DomAssetMonRptDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 报表ID
     */
    private String rptId;
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
    private String adminCode;
    /**
     * 行政区划名称
     */
    private String adminName;
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
     * 分域ID
     */
    private String domId;
    /**
     * 分域名称
     */
    private String domName;
    /**
     * 资产总数
     */
    private Integer totalAssetCount;
    /**
     * 在用资产数
     */
    private Integer inUseAssetCount;
    /**
     * 资产在用率
     */
    private BigDecimal assetInUseRate;
    /**
     * 维护总次数
     */
    private Integer totalMntCount;
    /**
     * 平均资产寿命
     */
    private BigDecimal avgAssetLife;
    /**
     * 资产故障事件数
     */
    private Integer assetFaultEvtCount;
    /**
     * 报表生成时间
     */
    private LocalDateTime rptCreateTime;
    /**
     * 报表生成人
     */
    private Long rptCreateUser;
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