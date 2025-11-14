package cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.comprehensiveReport.monptsummaryrpt;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 监测点位汇总报表 DO
 *
 * @author zcq
 */
@TableName("stat_mon_pt_summary_rpt")
@KeySequence("stat_mon_pt_summary_rpt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonPtSummaryRptDO extends BaseDO {

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
     * 点位总数量
     */
    private Integer totalPtCount;
    /**
     * 空气质量监测点位数
     */
    private Integer airPtCount;
    /**
     * 交通流量监测点位数
     */
    private Integer trafPtCount;
    /**
     * 井盖状态监测点位数
     */
    private Integer manholePtCount;
    /**
     * 其他类型点位数
     */
    private Integer otherPtCount;
    /**
     * 新增点位数量
     */
    private Integer newPtCount;
    /**
     * 停用点位数量
     */
    private Integer disablePtCount;
    /**
     * 点位覆盖率
     */
    private BigDecimal ptCoverageRate;
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