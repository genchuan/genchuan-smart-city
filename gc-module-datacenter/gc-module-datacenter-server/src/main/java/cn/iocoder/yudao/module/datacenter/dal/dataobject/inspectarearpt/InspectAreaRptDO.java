package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspectarearpt;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检区域统计 DO
 *
 * @author zcq
 */
@TableName("stat_inspect_area_rpt")
@KeySequence("stat_inspect_area_rpt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectAreaRptDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 统计ID
     */
    private String areaStatId;
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
    private String areaCode;
    /**
     * 行政区划名称
     */
    private String areaName;
    /**
     * 上级区域名称
     */
    private String parentAreaName;
    /**
     * 日常巡查次数
     */
    private Integer dailyInspectCount;
    /**
     * 专项巡查次数
     */
    private Integer specInspectCount;
    /**
     * 应急巡查次数
     */
    private Integer emerInspectCount;
    /**
     * 上报问题总数
     */
    private Integer totalProblemCount;
    /**
     * 整改完成数
     */
    private Integer rectifyCompleteCount;
    /**
     * 整改率
     */
    private BigDecimal rectifyRate;
    /**
     * 问题集中类型
     */
    private String concentratedProblemType;
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