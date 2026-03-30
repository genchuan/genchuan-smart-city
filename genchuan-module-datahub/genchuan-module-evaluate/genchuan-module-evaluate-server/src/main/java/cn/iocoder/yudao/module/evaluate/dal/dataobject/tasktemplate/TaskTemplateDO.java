package cn.iocoder.yudao.module.evaluate.dal.dataobject.tasktemplate;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 评价任务模板 DO
 *
 * @author 芋道源码
 */
@TableName("eval_task_template")
@KeySequence("eval_task_template_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskTemplateDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 评价任务模板ID（UUID）
     */
    private String templateId;
    /**
     * 模板名称
     */
    private String name;
    /**
     * 模板编码
     */
    private String code;
    /**
     * 适用对象类型ID（关联sys_object_type.type_id）
     */
    private String objectTypeId;
    /**
     * 关联指标体系ID（关联eval_index_system.system_id）
     */
    private String systemId;
    /**
     * 评价主体ID（关联eval_subject.subject_id）
     */
    private String subjectId;
    /**
     * 任务周期ID（关联sys_cycle_type.type_id）
     */
    private String cycleTypeId;
    /**
     * 描述信息
     */
    private String description;
    /**
     * 使用次数
     */
    private Integer useCount;
    /**
     * 最近使用时间
     */
    private LocalDateTime lastUseTime;
    /**
     * 状态ID（关联sys_status.status_id）
     */
    private Integer statusId;
    /**
     * 创建人ID（关联sys_user.user_id）
     */
    private String createBy;
    /**
     * 更新人ID（关联sys_user.user_id）
     */
    private String updateBy;
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
