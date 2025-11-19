package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.task.evaltasktemplate;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 任务模板管理 DO
 *
 * @author zcq
 */
@TableName("sys_eval_task_template")
@KeySequence("sys_eval_task_template_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalTaskTemplateDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 模板ID
     */
    private String taskTemplateId;
    /**
     * 模板名称
     */
    private String taskTemplateName;
    /**
     * 模板编码
     */
    private String taskTemplateCode;
    /**
     * 适用对象类型
     */
    private String applyObjectType;
    /**
     * 关联指标体系ID
     */
    private String idxSystemId;
    /**
     * 关联指标体系名称
     */
    private String idxSystemName;
    /**
     * 关联评价主体ID
     */
    private String evalSubjectId;
    /**
     * 关联评价主体名称
     */
    private String evalSubjectName;
    /**
     * 任务周期
     */
    private String taskCycle;
    /**
     * 自定义周期
     */
    private String customCycle;
    /**
     * 模板描述
     */
    private String templateDesc;
    /**
     * 启用状态
     */
    private String enableStatus;
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