package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.count.inspectproblemrpt;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检问题统计 DO
 *
 * @author zcq
 */
@TableName("stat_inspect_problem_rpt")
@KeySequence("stat_inspect_problem_rpt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectProblemRptDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 统计ID
     */
    private String problemStatId;
    /**
     * 统计周期
     */
    private String statCycle;
    /**
     * 统计周期名称
     */
    private String statCycleName;
    /**
     * 所属区域代码
     */
    private String areaCode;
    /**
     * 所属区域名称
     */
    private String areaName;
    /**
     * 问题类型ID
     */
    private String problemTypeId;
    /**
     * 问题类型名称
     */
    private String problemTypeName;
    /**
     * 上报问题数
     */
    private Integer rptProblemCount;
    /**
     * 一级问题数
     */
    private Integer level1ProblemCount;
    /**
     * 整改完成数
     */
    private Integer rectifyCompleteCount;
    /**
     * 整改率
     */
    private BigDecimal rectifyRate;
    /**
     * 未整改原因
     */
    private String unrectifiedReason;
    /**
     * 问题集中区域
     */
    private String concentratedArea;
    /**
     * 统计人
     */
    private String statUser;
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