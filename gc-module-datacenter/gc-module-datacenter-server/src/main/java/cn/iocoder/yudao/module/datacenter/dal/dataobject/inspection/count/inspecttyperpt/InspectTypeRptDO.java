package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.count.inspecttyperpt;

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
 * 巡查巡检类型统计 DO
 *
 * @author zcq
 */
@TableName("stat_inspect_type_rpt")
@KeySequence("stat_inspect_type_rpt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectTypeRptDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 统计ID
     */
    private String typeStatId;
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
     * 巡查类型
     */
    private String inspectType;
    /**
     * 巡查次数
     */
    private Integer inspectCount;
    /**
     * 参与人员数
     */
    private Integer participantCount;
    /**
     * 发现问题数
     */
    private Integer foundProblemCount;
    /**
     * 问题发现率
     */
    private BigDecimal problemFoundRate;
    /**
     * 整改完成数
     */
    private Integer rectifyCompleteCount;
    /**
     * 整改率
     */
    private BigDecimal rectifyRate;
    /**
     * 平均巡查时长
     */
    private Integer avgInspectEndure;
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