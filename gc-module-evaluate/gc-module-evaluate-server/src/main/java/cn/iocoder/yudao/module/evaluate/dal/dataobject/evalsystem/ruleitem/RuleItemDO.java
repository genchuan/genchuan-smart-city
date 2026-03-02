package cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.ruleitem;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 规则项 DO
 *
 * @author 亘川智城
 */
@TableName("eval_rule_item")
@KeySequence("eval_rule_item_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleItemDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 规则项ID（UUID）
     */
    private String ruleItemId;
    /**
     * 规则分类ID（关联eval_rule_category.rule_category_id）
     */
    private String ruleCategoryId;
    /**
     * 关联指标项ID（关联eval_index_item.item_id）
     */
    private String indexId;
    /**
     * 规则项名称
     */
    private String name;
    /**
     * 评分逻辑
     */
    private String scoreLogic;
    /**
     * 满分值
     */
    private BigDecimal fullScore;
    /**
     * 规则类型ID（关联sys_rule_type.type_id）
     */
    private String ruleTypeId;
    /**
     * 更新人ID（关联sys_user.user_id）
     */
    private String updateBy;
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