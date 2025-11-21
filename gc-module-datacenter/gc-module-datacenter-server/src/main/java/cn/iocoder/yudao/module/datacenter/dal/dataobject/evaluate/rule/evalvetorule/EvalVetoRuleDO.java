package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rule.evalvetorule;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 否决项规则管理 DO
 *
 * @author zcq
 */
@TableName("sys_eval_veto_rule")
@KeySequence("sys_eval_veto_rule_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalVetoRuleDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 否决项ID
     */
    private String vetoRuleId;
    /**
     * 否决项名称
     */
    private String vetoRuleName;
    /**
     * 否决项编码
     */
    private String vetoRuleCode;
    /**
     * 适用对象类型
     */
    private String applyObjectType;
    /**
     * 否决条件
     */
    private String vetoCondition;
    /**
     * 关联指标项ID
     */
    private String idxItemId;
    /**
     * 关联指标项名称
     */
    private String idxItemName;
    /**
     * 指标阈值
     */
    private String idxThreshold;
    /**
     * 生效周期
     */
    private String validCycle;
    /**
     * 否决结果
     */
    private String vetoResult;
    /**
     * 否决描述
     */
    private String vetoDesc;
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