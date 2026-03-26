package cn.iocoder.yudao.module.waterdetection.dal.dataobject.taskdispatch;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 任务派发 DO
 *
 * @author zcq
 */
@TableName("gc_task_dispatch")
@KeySequence("gc_task_dispatch_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDispatchDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 任务编号
     */
    private String taskCode;
    /**
     * 任务类型(常规/应急)
     */
    private String taskType;
    /**
     * 检测点清单
     */
    private String testPoints;
    /**
     * 指标清单
     */
    private String indicators;
    /**
     * 截止日期
     */
    private LocalDateTime deadline;
    /**
     * 派发部门
     */
    private String dispatchDept;

}