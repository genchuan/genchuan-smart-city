package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.count.inspectefficacyrpt;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检效率统计 DO
 *
 * @author zcq
 */
@TableName("stat_inspect_efficacy_rpt")
@KeySequence("stat_inspect_efficacy_rpt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectEfficacyRptDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 统计ID
     */
    private String efficacyStatId;
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
     * 参与巡查人员数
     */
    private Integer participantCount;
    /**
     * 总巡查次数
     */
    private Integer totalInspectCount;
    /**
     * 人均巡查次数
     */
    private BigDecimal perPersonInspectCount;
    /**
     * 平均单次巡查时长
     */
    private Integer avgSingleEndure;
    /**
     * 上报问题总数
     */
    private Integer totalProblemCount;
    /**
     * 问题平均整改时长
     */
    private Integer avgRectifyEndure;
    /**
     * 效率评估
     */
    private String efficacyEval;
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