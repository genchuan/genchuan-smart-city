package cn.iocoder.yudao.module.evaluate.dal.dataobject.ruledetail;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 评分规则明细 DO
 *
 * @author 亘川智城
 */
@TableName("eval_rule_detail")
@KeySequence("eval_rule_detail_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleDetailDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 规则ID(eval_comment_rule.id)
     */
    private Long ruleId;
    /**
     * 区间最小值（null表示无下限）
     */
    private BigDecimal minValue;
    /**
     * 区间最大值（null表示无上限）
     */
    private BigDecimal maxValue;
    /**
     * 最小值运算符（>=、>）
     */
    private String operatorMin;
    /**
     * 最大值运算符（<=、<）
     */
    private String operatorMax;
    /**
     * 该区间对应的分数
     */
    private BigDecimal score;
    /**
     * 排序优先级（值越小越优先匹配）
     */
    private Integer sortOrder;
    /**
     * 规则描述（如：=0、>1且<5）
     */
    private String remark;


}