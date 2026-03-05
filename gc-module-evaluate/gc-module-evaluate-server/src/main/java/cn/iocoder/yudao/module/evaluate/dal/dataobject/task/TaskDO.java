package cn.iocoder.yudao.module.evaluate.dal.dataobject.task;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 评价任务 DO
 *
 * @author 芋道源码
 */
@TableName("eval_task")
@KeySequence("eval_task_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 评价任务ID（UUID）
     */
    private String taskId;
    /**
     * 任务名称
     */
    private String name;
    /**
     * 任务编码
     */
    private String code;
    /**
     * 关联模板ID（关联eval_task_template.template_id）
     */
    private String templateId;
    /**
     * 评价对象范围ID（关联sys_scope.scope_id）
     */
    private String scopeId;
    /**
     * 任务开始时间
     */
    private LocalDateTime startTime;
    /**
     * 任务结束时间
     */
    private LocalDateTime endTime;
    /**
     * 数据采集方式ID（关联sys_collect_type.type_id）
     */
    private String collectTypeId;
    /**
     * 评价对象ID（关联eval_object.object_id）
     */
    private String objectId;
    /**
     * 总对象数
     */
    private Integer totalCount;
    /**
     * 已完成对象数
     */
    private Integer completedCount;
    /**
     * 完成率（如100.00）
     */
    private BigDecimal completionRate;
    /**
     * 任务状态ID（关联sys_task_status.status_id）
     */
    private Integer statusId;
    /**
     * 创建人ID（关联sys_user.user_id）
     */
    private String createBy;
    /**
     * 创建时间（业务字段）
     */
    private LocalDateTime bizCreateTime;
    /**
     * 更新时间（业务字段）
     */
    private LocalDateTime bizUpdateTime;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    private String extCommon4;
    /**
     * 评价对象范围
     */
    private String objectScope;
    /**
     * 原结束时间
     */
    private LocalDateTime originalEndTime;
    /**
     * 未完成对象数
     */
    private Integer uncompletedObject;
    /**
     * 取消原因
     */
    private String cancelReason;
    /**
     * 取消时间
     */
    private LocalDateTime cancelTime;
    /**
     * 取消操作人，关联sys_user.user_id
     */
    private String cancelBy;
}
