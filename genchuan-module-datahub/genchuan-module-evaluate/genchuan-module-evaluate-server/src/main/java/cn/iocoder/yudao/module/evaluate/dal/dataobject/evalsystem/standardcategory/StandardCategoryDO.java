package cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.standardcategory;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 标准分类 DO
 *
 * @author 亘川智城
 */
@TableName("eval_standard_category")
@KeySequence("eval_standard_category_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StandardCategoryDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 标准分类ID（UUID）
     */
    private String standardCategoryId;
    /**
     * 标准分类名称
     */
    private String name;
    /**
     * 适用指标体系ID（关联eval_index_system.system_id）
     */
    private String systemId;
    /**
     * 标准项数量
     */
    private Integer itemCount;
    /**
     * 状态ID（关联sys_status.status_id）
     */
    private Integer statusId;
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
     * 变更日志
     */
    private String changeLog;
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