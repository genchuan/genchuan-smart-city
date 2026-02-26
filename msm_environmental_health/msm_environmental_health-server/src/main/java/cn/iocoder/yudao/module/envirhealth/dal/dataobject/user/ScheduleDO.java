package cn.iocoder.yudao.module.envirhealth.dal.dataobject.user;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 排班计划 DO
 *
 * @author 芋道源码
 */
@TableName("sys_schedule")
@KeySequence("sys_schedule_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 主键（UUID）
     */
    private String scheduleId;
    /**
     * 关联sys_user.id
     */
    private String userId;
    /**
     * 关联sys_job_type.id
     */
    private String jobTypeId;
    /**
     * 关联sys_team.id
     */
    private String teamId;
    /**
     * 关联sys_area.area_code
     */
    private String areaCode;
    /**
     * 排班周期：日/周/月
     */
    private String cycle;
    /**
     * 作业时段
     */
    private String workTimePeriod;
    /**
     * 关联sys_schedule_status.id
     */
    private String scheduleStatusId;
    /**
     * 换班申请状态：无/待审核/已通过/已拒绝
     */
    private String swapStatus;
    /**
     * 换班申请人ID，关联sys_user.id
     */
    private String swapApplicantId;
    /**
     * 被换班人员ID，关联sys_user.id
     */
    private String swapTargetId;
    /**
     * 换班日期
     */
    private LocalDateTime swapDate;
    /**
     * 换班理由
     */
    private String swapReason;
    /**
     * 审核结果：通过/拒绝
     */
    private String reviewResult;
    /**
     * 审核意见
     */
    private String reviewOpinion;
    /**
     * 排班覆盖率
     */
    private BigDecimal coverageRate;
    /**
     * 岗位空缺提醒：是/否
     */
    private String vacancyReminder;
    /**
     * 换班申请数
     */
    private Integer swapApplyCount;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    private String extCommon4;
    /**
     * 关联sys_user.id
     */
    private String createBy;

}