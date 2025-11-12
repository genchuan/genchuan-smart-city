package cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.comprehensiveReport.trendanalysisrpt;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 综合趋势分析报表 DO
 *
 * @author zcq
 */
@TableName("stat_trend_analysis_rpt")
@KeySequence("stat_trend_analysis_rpt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrendAnalysisRptDO extends BaseDO {

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
     * 分析维度类型
     */
    private String trendDimType;
    /**
     * 分析维度ID
     */
    private String trendDimId;
    /**
     * 分析维度名称
     */
    private String trendDimName;
    /**
     * 时间粒度
     */
    private String timeGranularity;
    /**
     * 起始时间
     */
    private LocalDate startTime;
    /**
     * 结束时间
     */
    private LocalDate endTime;
    /**
     * 周期1标识
     */
    private String cycle1Label;
    /**
     * 周期1数值
     */
    private BigDecimal cycle1Value;
    /**
     * 周期2标识
     */
    private String cycle2Label;
    /**
     * 周期2数值
     */
    private BigDecimal cycle2Value;
    /**
     * 整体变化率
     */
    private BigDecimal overallChangeRate;
    /**
     * 趋势类型
     */
    private String trendType;
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