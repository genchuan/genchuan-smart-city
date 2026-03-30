package cn.iocoder.yudao.module.park.dal.dataobject.park.user.maintainschedule;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 运维排班 DO
 *
 * @author 亘川智城
 */
@TableName("park_maintain_schedule")
@KeySequence("park_maintain_schedule_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaintainScheduleDO extends BaseDO {

    /**
     * [主键ID]
     */
    @TableId
    private Long id;
    /**
     * [运维人员ID] 关联park_maintain_user.id
     */
    private Long maintainUserId;
    /**
     * [部门ID] 关联park_dept.id
     */
    private Long deptId;
    /**
     * [排班日期]
     */
    private LocalDate scheduleDate;
    /**
     * [班次类型] 如:早班/中班/晚班/夜班
     */
    private String shiftType;
    /**
     * [上班时间]
     */
    private LocalTime startTime;
    /**
     * [下班时间]
     */
    private LocalTime endTime;
    /**
     * [状态] 如:正常/调班/取消
     */
    private String status;
    /**
     * [调整原因] 可为NULL
     */
    private String adjustReason;
    /**
     * [排班人ID] 关联park_user.id
     */
    private Long createBy;
    /**
     * [备注]
     */
    private String remark;
    /**
     * [通用扩展字段1]
     */
    private String extCommon1;
    /**
     * [通用扩展字段2]
     */
    private String extCommon2;
    /**
     * [通用扩展字段3]
     */
    private String extCommon3;
    /**
     * [通用扩展字段4]
     */
    private String extCommon4;

}
