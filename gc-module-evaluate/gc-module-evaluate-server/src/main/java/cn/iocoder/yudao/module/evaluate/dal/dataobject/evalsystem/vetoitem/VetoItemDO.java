package cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.vetoitem;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 否决项 DO
 *
 * @author 芋道源码
 */
@TableName("eval_veto_item")
@KeySequence("eval_veto_item_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VetoItemDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 否决项ID（UUID）
     */
    private String vetoItemId;
    /**
     * 否决项名称
     */
    private String name;
    /**
     * 适用对象类型ID（关联sys_object_type.type_id）
     */
    private Integer objectTypeId;
    /**
     * 否决条件
     */
    private String condition;
    /**
     * 生效周期
     */
    private String validCycle;
    /**
     * 否决项数量
     */
    private Integer count;
    /**
     * 状态ID（关联sys_status.status_id）
     */
    private Integer statusId;
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
