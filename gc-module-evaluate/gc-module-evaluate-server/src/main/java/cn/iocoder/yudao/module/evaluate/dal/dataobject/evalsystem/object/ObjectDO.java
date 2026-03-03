package cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.object;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 评价对象 DO
 *
 * @author 亘川智城
 */
@TableName("eval_object")
@KeySequence("eval_object_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObjectDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 评价对象ID（UUID）
     */
    private String objectId;
    /**
     * 对象名称
     */
    private String name;
    /**
     * 对象编码
     */
    private String code;
    /**
     * 所属区域编码（关联sys_area.area_code）
     */
    private String areaCode;
    /**
     * 对象类型ID（关联sys_object_type.type_id）
     */
    private String objectTypeId;
    /**
     * 负责人ID（关联sys_user.user_id）
     */
    private String managerId;
    /**
     * 关联网格/部门ID（关联eval_related_object.related_id）
     */
    private String relatedId;
    /**
     * 状态ID（关联sys_status.status_id）
     */
    private String statusId;
    /**
     * 创建人ID（关联sys_user.user_id）
     */
    private String createBy;
    /**
     * 创建时间（业务字段）
     */
    private LocalDateTime bizCreateTime;
    /**
     * 更新人ID（关联sys_user.user_id）
     */
    private String updateBy;
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