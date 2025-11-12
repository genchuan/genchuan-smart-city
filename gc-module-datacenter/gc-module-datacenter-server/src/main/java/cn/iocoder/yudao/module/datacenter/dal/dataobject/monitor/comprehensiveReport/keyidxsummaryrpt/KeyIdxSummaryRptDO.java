package cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.comprehensiveReport.keyidxsummaryrpt;

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
 * 关键指标汇总报表 DO
 *
 * @author zcq
 */
@TableName("stat_key_idx_summary_rpt")
@KeySequence("stat_key_idx_summary_rpt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeyIdxSummaryRptDO extends BaseDO {

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
     * 指标ID
     */
    private String idxId;
    /**
     * 指标名称
     */
    private String idxName;
    /**
     * 指标单位
     */
    private String idxUnit;
    /**
     * 指标重要等级
     */
    private String idxLevel;
    /**
     * 指标平均值
     */
    private BigDecimal idxAvg;
    /**
     * 指标最大值
     */
    private String idxMax;
    /**
     * 超标次数
     */
    private Integer exceedCount;
    /**
     * 环比改善率
     */
    private BigDecimal momImproveRate;
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