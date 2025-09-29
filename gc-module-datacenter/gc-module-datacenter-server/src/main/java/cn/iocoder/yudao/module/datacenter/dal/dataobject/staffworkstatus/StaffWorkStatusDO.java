package cn.iocoder.yudao.module.datacenter.dal.dataobject.staffworkstatus;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 人员作业状态 DO
 *
 * @author zcq
 */
@TableName("gc_staff_work_status")
@KeySequence("gc_staff_work_status_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffWorkStatusDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 监控ID
     */
    private String monitorId;
    /**
     * 人员ID
     */
    private String staffId;
    /**
     * 人员姓名
     */
    private String staffName;
    /**
     * 作业状态
     */
    private String workStatus;
    /**
     * 当前任务ID
     */
    private String currentTaskId;
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * GPS坐标
     */
    private String gpsCoordinates;
    /**
     * 定位时间
     */
    private LocalDateTime locationTime;
    /**
     * 已完成任务数
     */
    private Integer completedTaskCount;
    /**
     * 剩余任务数
     */
    private Integer remainingTaskCount;
    /**
     * 异常状态
     */
    private String abnormalStatus;
    /**
     * 异常开始时间
     */
    private LocalDateTime abnormalStartTime;
    /**
     * 所属区域ID
     */
    private String areaId;

}