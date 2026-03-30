package cn.iocoder.yudao.module.evaluate.dal.dataobject.plan;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 考察计划 DO
 *
 * @author 芋道源码
 */
@TableName("inspection_plan")
@KeySequence("inspection_plan_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 考察计划ID（UUID）
     */
    private String planId;
    /**
     * 计划名称
     */
    private String name;
    /**
     * 计划编码
     */
    private String code;
    /**
     * 关联评价任务ID（关联eval_task.task_id）
     */
    private String taskId;
    /**
     * 考察时间
     */
    private LocalDateTime inspectionTime;
    /**
     * 考察方式ID（关联sys_inspection_type.type_id）
     */
    private String inspectionTypeId;
    /**
     * 通知状态ID（关联sys_notify_status.status_id）
     */
    private String notifyStatusId;
    /**
     * 确认人数
     */
    private Integer confirmCount;
    /**
     * 计划状态ID（关联sys_plan_status.status_id）
     */
    private String statusId;
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


}
