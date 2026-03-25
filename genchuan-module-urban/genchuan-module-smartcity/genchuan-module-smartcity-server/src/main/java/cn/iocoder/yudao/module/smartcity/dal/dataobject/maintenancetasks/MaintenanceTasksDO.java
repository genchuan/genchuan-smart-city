package cn.iocoder.yudao.module.smartcity.dal.dataobject.maintenancetasks;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 养护任务 DO
 *
 * @author zcq
 */
@TableName("smartcity_maintenance_tasks")
@KeySequence("smartcity_maintenance_tasks_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceTasksDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 任务编号
     */
    private String taskNumber;
    /**
     * 任务名称
     */
    private String task;
    /**
     * 养护地块
     */
    private String maintainTheLandParcel;
    /**
     * 养护人员
     */
    private String maintenancePersonnel;
    /**
     * 任务开始时间
     */
    private LocalDateTime taskStartTime;
    /**
     * 任务结束时间
     */
    private LocalDateTime taskEndTime;
    /**
     * 任务内容
     */
    private String taskContent;
    /**
     * 所需工具
     */
    private String requiredTools;
    /**
     * 所需材料
     */
    private String requiredMaterials;
    /**
     * 任务优先级
     */
    private String taskPriority;
    /**
     * 任务状态
     */
    private String status;
    /**
     * 完成情况说明
     */
    private String completionStatusDescription;

}