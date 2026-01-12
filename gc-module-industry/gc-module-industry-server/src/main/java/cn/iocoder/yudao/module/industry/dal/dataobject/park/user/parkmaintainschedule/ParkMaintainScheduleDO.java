package cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkmaintainschedule;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 运维排班 DO
 *
 * @author lxs
 */
@TableName("park_maintain_schedule")
@KeySequence("park_maintain_schedule_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkMaintainScheduleDO extends BaseDO {

    /**
     * [主键ID] 排班记录唯一标识
     */
    @TableId
    private Long id;
    /**
     * [运维人员ID] 关联 park_maintain_user.id
     */
    private Long maintainUserId;
    /**
     * [排班日期] 排班日期
     */
    private LocalDate scheduleDate;
    /**
     * [班次类型] 早班 / 中班 / 晚班 / 全天
     */
    private String shiftType;
    /**
     * [上班时间] 上班时间
     */
    private LocalTime startTime;
    /**
     * [下班时间] 下班时间
     */
    private LocalTime endTime;
    /**
     * [状态] 排班状态：正常 / 调班 / 取消
     */
    private String status;
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
    /**
     * [备注] 排班相关备注说明
     */
    private String remark;

}
