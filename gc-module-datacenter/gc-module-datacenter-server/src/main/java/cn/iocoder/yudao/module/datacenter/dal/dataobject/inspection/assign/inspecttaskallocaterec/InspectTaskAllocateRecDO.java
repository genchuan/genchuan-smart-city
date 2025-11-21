package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.assign.inspecttaskallocaterec;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检任务分配记录 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_task_allocate_rec")
@KeySequence("biz_inspect_task_allocate_rec_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectTaskAllocateRecDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 分配ID
     */
    private String allocateId;
    /**
     * 任务ID
     */
    private String taskId;
    /**
     * 任务编码
     */
    private String taskCode;
    /**
     * 原负责人员ID
     */
    private String oldPersonId;
    /**
     * 原负责人员姓名
     */
    private String oldPersonName;
    /**
     * 新负责人员ID
     */
    private String newPersonId;
    /**
     * 新负责人员姓名
     */
    private String newPersonName;
    /**
     * 分配原因
     */
    private String allocateReason;
    /**
     * 分配时间
     */
    private LocalDateTime allocateTime;
    /**
     * 分配人
     */
    private String allocateUser;
    /**
     * 任务状态变更
     */
    private String taskStatusChange;
    /**
     * 任务类型
     */
    private String taskType;
    /**
     * 分类扩展字段1
     */
    private String extCat1;
    /**
     * 分类扩展字段2
     */
    private String extCat2;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;

}