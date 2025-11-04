package cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.rpt.evalgridrpt;

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
 * 评价网格统计 DO
 *
 * @author zhucongquan
 */
@TableName("stat_eval_grid_rpt")
@KeySequence("stat_eval_grid_rpt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalGridRptDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 统计ID
     */
    private String evalStatId;
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
     * 评价网格类型(A/B/C)
     */
    private String gridType;
    /**
     * 关联评价指标ID
     */
    private String idxId;
    /**
     * 关联评价指标名称
     */
    private String idxName;
    /**
     * 网格总数(个)
     */
    private Integer totalCount;
    /**
     * 总面积(m²)
     */
    private Integer totalArea;
    /**
     * 平均所含管理数(个)
     */
    private BigDecimal avgMgCount;
    /**
     * 总所含管理网格数(个)
     */
    private Integer totalMgCount;
    /**
     * 类型占比(%)
     */
    private BigDecimal typeRatio;
    /**
     * 新增网格数(个)
     */
    private Integer newCount;
    /**
     * 统计人
     */
    private String statUserId;
    /**
     * 统计时间
     */
    private LocalDateTime statTime;
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