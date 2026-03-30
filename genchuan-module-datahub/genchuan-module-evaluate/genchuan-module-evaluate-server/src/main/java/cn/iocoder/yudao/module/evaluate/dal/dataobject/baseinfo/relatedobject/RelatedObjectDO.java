package cn.iocoder.yudao.module.evaluate.dal.dataobject.baseinfo.relatedobject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 关联对象 DO
 *
 * @author 亘川智城
 */
@TableName("eval_related_object")
@KeySequence("eval_related_object_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelatedObjectDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 关联对象ID（UUID）
     */
    private String relatedId;
    /**
     * 关联对象名称
     */
    private String relatedName;
    /**
     * 关联对象类型：关联sys_object_type.type_id
     */
    private String relatedType;
    /**
     * 关联对象编码
     */
    private String relatedCode;
    /**
     * 更新人，关联sys_user.user_id
     */
    private String updateBy;
    /**
     * 上级关联对象ID（关联eval_related_object.related_id）
     */
    private String parentId;
    /**
     * 状态ID（关联sys_status.status_id）
     */
    private Integer statusId;
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