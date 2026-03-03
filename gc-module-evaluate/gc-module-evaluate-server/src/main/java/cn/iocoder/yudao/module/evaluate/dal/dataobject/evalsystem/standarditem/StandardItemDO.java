package cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.standarditem;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 标准项 DO
 *
 * @author 亘川智城
 */
@TableName("eval_standard_item")
@KeySequence("eval_standard_item_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StandardItemDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 标准项ID（UUID）
     */
    private String standardItemId;
    /**
     * 标准分类ID（关联eval_standard_category.standard_category_id）
     */
    private String standardCategoryId;
    /**
     * 标准项等级
     */
    private String grade;
    /**
     * 分数范围
     */
    private String scoreRange;
    /**
     * 排序序号
     */
    private Integer sortNo;
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