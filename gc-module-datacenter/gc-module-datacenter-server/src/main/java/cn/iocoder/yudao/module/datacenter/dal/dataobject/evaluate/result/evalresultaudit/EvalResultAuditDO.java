package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultaudit;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 结果审核 DO
 *
 * @author zcq
 */
@TableName("sys_eval_result_audit")
@KeySequence("sys_eval_result_audit_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalResultAuditDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 审核ID
     */
    private String resultAuditId;
    /**
     * 关联任务ID
     */
    private String evalTaskId;
    /**
     * 关联任务名称
     */
    private String evalTaskName;
    /**
     * 评价对象ID
     */
    private String evalObjectId;
    /**
     * 评价对象名称
     */
    private String evalObjectName;
    /**
     * 自动计算结果
     */
    private String autoCalcResult;
    /**
     * 自动计算得分
     */
    private BigDecimal autoCalcScore;
    /**
     * 审核状态
     */
    private String auditStatus;
    /**
     * 审核人ID
     */
    private String auditorId;
    /**
     * 审核人姓名
     */
    private String auditorName;
    /**
     * 审核意见
     */
    private String auditOpinion;
    /**
     * 审核时间
     */
    private LocalDateTime auditTime;
    /**
     * 创建人(业务)
     */
    private String createUserBiz;
    /**
     * 创建时间(业务)
     */
    private LocalDateTime createTimeBiz;
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