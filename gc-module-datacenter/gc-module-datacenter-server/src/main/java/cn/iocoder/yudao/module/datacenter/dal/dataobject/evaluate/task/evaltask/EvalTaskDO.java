package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.task.evaltask;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 任务管理 DO
 *
 * @author zcq
 */
@TableName("sys_eval_task")
@KeySequence("sys_eval_task_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalTaskDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 任务ID
     */
    private String evalTaskId;
    /**
     * 任务名称
     */
    private String evalTaskName;
    /**
     * 任务编码
     */
    private String evalTaskCode;
    /**
     * 关联模板ID
     */
    private String taskTemplateId;
    /**
     * 关联模板名称
     */
    private String taskTemplateName;
    /**
     * 评价对象范围
     */
    private String objectScope;
    /**
     * 关联对象IDs
     */
    private String relateObjectIds;
    /**
     * 关联对象Names
     */
    private String relateObjectNames;
    /**
     * 任务开始时间
     */
    private LocalDateTime taskStartTime;
    /**
     * 任务结束时间
     */
    private LocalDateTime taskEndTime;
    /**
     * 任务状态
     */
    private String taskStatus;
    /**
     * 数据采集方式
     */
    private String dataCollectMethod;
    /**
     * 任务描述
     */
    private String taskDesc;
    /**
     * 创建人(业务)
     */
    private String createUserBiz;
    /**
     * 创建时间(业务)
     */
    private LocalDateTime createTimeBiz;
    /**
     * 更新人(业务)
     */
    private String updateUserBiz;
    /**
     * 更新时间(业务)
     */
    private LocalDateTime updateTimeBiz;
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