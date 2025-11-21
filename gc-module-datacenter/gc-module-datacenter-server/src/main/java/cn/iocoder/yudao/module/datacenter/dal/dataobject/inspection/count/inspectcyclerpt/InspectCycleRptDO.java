package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.count.inspectcyclerpt;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检周期统计 DO
 *
 * @author zcq
 */
@TableName("stat_inspect_cycle_rpt")
@KeySequence("stat_inspect_cycle_rpt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectCycleRptDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 统计ID
     */
    private String cycleStatId;
    /**
     * 统计维度
     */
    private String statDimension;
    /**
     * 所属区域代码
     */
    private String areaCode;
    /**
     * 所属区域名称
     */
    private String areaName;
    /**
     * 周期列表
     */
    private String cycleList;
    /**
     * 巡查次数列表
     */
    private String inspectCountList;
    /**
     * 问题数列表
     */
    private String problemCountList;
    /**
     * 整改率列表
     */
    private String rectifyRateList;
    /**
     * 平均问题发现率列表
     */
    private String avgFoundRateList;
    /**
     * 统计人
     */
    private String statUser;
    /**
     * 统计时间(业务)
     */
    private LocalDateTime statTimeBiz;
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