package cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexitem;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 指标项 DO
 *
 * @author 亘川智城
 */
@TableName("eval_index_item")
@KeySequence("eval_index_item_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndexItemDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 指标项ID（UUID）
     */
    private String itemId;
    /**
     * 指标分类ID（关联eval_index_category.category_id）
     */
    private String categoryId;
    /**
     * 指标项名称
     */
    private String name;
    /**
     * 指标类型ID（关联sys_index_type.type_id）
     */
    private String indexTypeId;
    /**
     * 计算方式ID（关联sys_calc_way.way_id）
     */
    private String calcWayId;
    /**
     * 达标阈值
     */
    private BigDecimal threshold;
    /**
     * 指标项权重
     */
    private BigDecimal weight;
    /**
     * 排序序号
     */
    private Integer sortNo;
    /**
     * 创建人，关联sys_user.user_id
     */
    private String createBy;
    /**
     * 更新人，关联sys_user.user_id
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
    /**
     * 评价规则id(关联eval_comment_rule.id)
     */
    private Long commentRuleId;

    /**
     * 规则分类id(关联eval_rule_category主键id)
     */
    private Long commentCategoryId;

}