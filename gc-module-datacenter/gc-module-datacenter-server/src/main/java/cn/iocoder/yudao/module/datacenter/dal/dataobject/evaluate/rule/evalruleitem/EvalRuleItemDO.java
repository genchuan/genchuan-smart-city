package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rule.evalruleitem;

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
 * 规则项管理 DO
 *
 * @author zcq
 */
@TableName("sys_eval_rule_item")
@KeySequence("sys_eval_rule_item_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalRuleItemDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 规则项ID
     */
    private String ruleItemId;
    /**
     * 规则项名称
     */
    private String ruleItemName;
    /**
     * 规则项编码
     */
    private String ruleItemCode;
    /**
     * 所属规则分类ID
     */
    private String ruleCatId;
    /**
     * 所属规则分类名称
     */
    private String ruleCatName;
    /**
     * 关联指标项ID
     */
    private String idxItemId;
    /**
     * 关联指标项名称
     */
    private String idxItemName;
    /**
     * 评分逻辑
     */
    private String scoreLogic;
    /**
     * 满分值
     */
    private BigDecimal fullScore;
    /**
     * 规则类型
     */
    private String ruleType;
    /**
     * 规则描述
     */
    private String ruleDesc;
    /**
     * 启用状态
     */
    private String enableStatus;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 更新人
     */
    private String updateUser;
    /**
     * 更新时间
     */
    private LocalDateTime updateTimeSys;
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