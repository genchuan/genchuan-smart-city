package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.inspect.evalinspectplan;

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
 * 考察计划管理 DO
 *
 * @author zcq
 */
@TableName("sys_eval_inspect_plan")
@KeySequence("sys_eval_inspect_plan_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalInspectPlanDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 计划ID
     */
    private String inspectPlanId;
    /**
     * 计划名称
     */
    private String inspectPlanName;
    /**
     * 计划编码
     */
    private String inspectPlanCode;
    /**
     * 关联任务ID
     */
    private String evalTaskId;
    /**
     * 关联任务名称
     */
    private String evalTaskName;
    /**
     * 考察对象ID
     */
    private String evalObjectId;
    /**
     * 考察对象名称
     */
    private String evalObjectName;
    /**
     * 考察人员ID
     */
    private String inspectorId;
    /**
     * 考察人员姓名
     */
    private String inspectorName;
    /**
     * 考察时间
     */
    private LocalDateTime inspectTime;
    /**
     * 考察方式
     */
    private String inspectMethod;
    /**
     * 考察内容
     */
    private String inspectContent;
    /**
     * 计划状态
     */
    private String planStatus;
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