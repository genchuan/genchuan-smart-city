package cn.iocoder.yudao.module.envirhealth.dal.dataobject.user;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 考勤 DO
 *
 * @author 芋道源码
 */
@TableName("sys_attendance")
@KeySequence("sys_attendance_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 主键（UUID）
     */
    private String attendanceId;
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
     * 打卡日期
     */
    private LocalDateTime checkDate;
    /**
     * 到岗打卡时间
     */
    private LocalDateTime onDutyTime;
    /**
     * 离岗打卡时间
     */
    private LocalDateTime offDutyTime;
    /**
     * 关联sys_attendance_status.id
     */
    private String attendanceStatusId;
    /**
     * 打卡位置
     */
    private String checkLocation;
    /**
     * 考勤时长（单位：小时）
     */
    private BigDecimal workHours;
    /**
     * 关联sys_attendance_abnormal_type.id
     */
    private String abnormalTypeId;
    /**
     * 异常说明
     */
    private String abnormalDesc;
    /**
     * 佐证材料URL
     */
    private String proofMaterial;
    /**
     * 关联sys_review_status.id
     */
    private String reviewStatusId;
    /**
     * 补录理由
     */
    private String supplementReason;
    /**
     * 补录时间
     */
    private LocalDateTime supplementTime;
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

}