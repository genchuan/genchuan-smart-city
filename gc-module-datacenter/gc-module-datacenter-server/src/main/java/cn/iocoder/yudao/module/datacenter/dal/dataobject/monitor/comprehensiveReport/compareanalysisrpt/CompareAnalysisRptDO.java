package cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.comprehensiveReport.compareanalysisrpt;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 综合对比分析报表 DO
 *
 * @author zcq
 */
@TableName("stat_compare_analysis_rpt")
@KeySequence("stat_compare_analysis_rpt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompareAnalysisRptDO extends BaseDO {

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
     * 对比维度类型
     */
    private String compareDimType;
    /**
     * 统计周期
     */
    private String statCycle;
    /**
     * 统计周期名称
     */
    private String statCycleName;
    /**
     * 统计指标
     */
    private String statIdx;
    /**
     * 对比对象1ID
     */
    private String compareObj1Id;
    /**
     * 对比对象1名称
     */
    private String compareObj1Name;
    /**
     * 对比对象1数值
     */
    private BigDecimal compareObj1Value;
    /**
     * 对比对象2ID
     */
    private String compareObj2Id;
    /**
     * 对比对象2名称
     */
    private String compareObj2Name;
    /**
     * 对比对象2数值
     */
    private BigDecimal compareObj2Value;
    /**
     * 平均值
     */
    private BigDecimal avgValue;
    /**
     * 最大值对象ID
     */
    private String maxObjId;
    /**
     * 最大值对象名称
     */
    private String maxObjName;
    /**
     * 最大值
     */
    private BigDecimal maxValue;
    /**
     * 最小值对象ID
     */
    private String minObjId;
    /**
     * 最小值对象名称
     */
    private String minObjName;
    /**
     * 最小值
     */
    private BigDecimal minValue;
    /**
     * 最大差异值
     */
    private BigDecimal maxDiffValue;
    /**
     * 最大差异率
     */
    private BigDecimal maxDiffRate;
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