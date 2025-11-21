package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.report.inspectproblemlevelrec;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检问题分级记录 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_problem_level_rec")
@KeySequence("biz_inspect_problem_level_rec_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectProblemLevelRecDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 分级ID
     */
    private String problemLevelId;
    /**
     * 关联问题ID
     */
    private String problemId;
    /**
     * 关联问题编码
     */
    private String problemCode;
    /**
     * 关联问题名称
     */
    private String problemName;
    /**
     * 问题级别
     */
    private String problemLevel;
    /**
     * 级别描述
     */
    private String levelDesc;
    /**
     * 处置时限
     */
    private Integer handleDeadline;
    /**
     * 分级依据
     */
    private String levelBasis;
    /**
     * 分级人
     */
    private String levelUser;
    /**
     * 分级时间
     */
    private LocalDateTime levelTime;
    /**
     * 是否推送提醒
     */
    private Boolean isPushRemind;
    /**
     * 提醒推送时间
     */
    private LocalDateTime pushRemindTime;
    /**
     * 问题状态变更
     */
    private String problemStatusChange;
    /**
     * 创建人
     */
    private String bizCreateUser;
    /**
     * 创建时间
     */
    private LocalDateTime bizCreateTime;
    /**
     * 更新人
     */
    private String bizUpdateUser;
    /**
     * 更新时间
     */
    private LocalDateTime bizUpdateTime;
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