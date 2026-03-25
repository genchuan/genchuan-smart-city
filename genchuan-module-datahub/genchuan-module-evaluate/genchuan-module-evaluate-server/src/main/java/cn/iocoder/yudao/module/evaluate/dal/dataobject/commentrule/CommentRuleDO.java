package cn.iocoder.yudao.module.evaluate.dal.dataobject.commentrule;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 评分规则主 DO
 *
 * @author 亘川智城
 */
@TableName("eval_comment_rule")
@KeySequence("eval_comment_rule_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentRuleDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 指标体系ID(关联eval_index_system)
     */
    private Long systemId;
    /**
     * 规则分类ID(关联eval_rule_category)
     */
    private Long ruleCategoryId;
    /**
     * 规则名称
     */
    private String ruleName;
    /**
     * 规则类型（1=加分，2=扣分）
     */
    private Integer ruleType;
    /**
     * 状态（1=启用，2=停用）
     */
    private Integer status;
    /**
     * 适用对象类型（如：网格/企业/个人）
     */
    private String applyObjectType;
    /**
     * 生效开始时间
     */
    private LocalDateTime effectiveStartTime;
    /**
     * 生效结束时间
     */
    private LocalDateTime effectiveEndTime;
    /**
     * 状态变更备注
     */
    private String statusChangeRemark;
    /**
     * 操作变更日志
     */
    private String operationLog;


}