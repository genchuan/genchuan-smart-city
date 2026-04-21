package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.behaviormgmt;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 行为管理 DO
 *
 * @author 芋道源码
 */
@TableName("behavior_mgmt")
@KeySequence("behavior_mgmt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BehaviorMgmtDO extends BaseDO {

    /**
     * 主键 ID
     */
    @TableId
    private Long id;
    /**
     * 学生 ID
     */
    private Long studentId;
    /**
     * 请假类型：事假/病假/其他
     */
    private String leaveType;
    /**
     * 请假开始时间
     */
    private LocalDateTime startTime;
    /**
     * 请假结束时间
     */
    private LocalDateTime endTime;
    /**
     * 请假原因
     */
    private String leaveReason;
    /**
     * 审批级别：班主任/辅导员
     */
    private String auditLevel;
    /**
     * 审批人
     */
    private String auditUser;
    /**
     * 审批时间
     */
    private LocalDateTime auditTime;
    /**
     * 考勤同步状态：未同步/已同步
     */
    private String attendanceSync;
    /**
     * 状态：待审批/已通过/已驳回
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备用字段 1
     */
    private String reserve1;
    /**
     * 备用字段 2
     */
    private String reserve2;


}