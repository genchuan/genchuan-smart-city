package cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.rulecategory;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 规则分类管理 DO
 *
 * @author 亘川智城
 */
@TableName("eval_rule_category")
@KeySequence("eval_rule_category_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleCategoryDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 规则分类名称
     */
    private String name;
    /**
     * 适用指标体系ID（关联eval_index_system.id）
     */
    private String systemId;
    /**
     * 规则项数量
     */
    private Integer itemCount;
    /**
     * 状态ID（关联sys_status.id）
     */
    private Integer statusId;
    /**
     * 使用次数
     */
    private Integer useCount;
    /**
     * 最近使用时间
     */
    private LocalDateTime lastUseTime;
    /**
     * 变更日志关联“规则分类表”，截取前50字
     */
    private String changeLog;
    /**
     * 指标项名称ideval_rule_item.id关联“指标项表”
     */
    private Integer itemId;
    /**
     * 规则项id关联“规则类型字典表”sys_rule_type
     */
    private Integer ruleId;
    /**
     * 对象类型ID关联“对象类型字典表”sys_object_type
     */
    private Integer objectTypeId;

}