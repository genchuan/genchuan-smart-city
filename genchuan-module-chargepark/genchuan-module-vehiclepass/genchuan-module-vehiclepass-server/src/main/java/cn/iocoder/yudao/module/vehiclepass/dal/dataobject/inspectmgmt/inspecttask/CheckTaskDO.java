package cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inspectmgmt.inspecttask;

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
 * 稽查任务 DO
 *
 * @author 亘川智城
 */
@TableName("check_task")
@KeySequence("check_task_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckTaskDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 任务类型：违规通行稽查/欠费逃费稽查/其他 关联字典inspect_task_task_type
     */
    private String taskType;
    /**
     * 派发时间
     */
    private LocalDateTime dispatchTime;
    /**
     * 截止时间
     */
    private LocalDateTime deadlineTime;
    /**
     * 状态：待派发/待认领/处理中/已完成/已归档 关联字典inspect_task_status
     */
    private String status;
    /**
     * 片区ID，关联片区表
     */
    private Long areaId;
    /**
     * 执行人ID，关联system_user用户表
     */
    private Long executeUserId;
    /**
     * 完成时间
     */
    private LocalDateTime finishTime;
    /**
     * 任务进度
     */
    private String taskProgress;
    /**
     * 转派理由
     */
    private String transferReason;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}