package cn.iocoder.yudao.module.inspectop.dal.dataobject.inspecttask;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡检任务 DO
 *
 * @author zhucongquan
 */
@TableName("inspect_task")
@KeySequence("inspect_task_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectTaskDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 关联计划ID
     */
    private Long planId;
    /**
     * 巡检人员ID
     */
    private Long userId;
    /**
     * 派发时间
     */
    private LocalDateTime dispatchTime;
    /**
     * 认领时间
     */
    private LocalDateTime claimTime;
    /**
     * 任务状态
     */
    private String status;
    /**
     * 执行进度
     */
    private Integer progress;
    /**
     * 是否归档
     */
    private Boolean isArchive;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}