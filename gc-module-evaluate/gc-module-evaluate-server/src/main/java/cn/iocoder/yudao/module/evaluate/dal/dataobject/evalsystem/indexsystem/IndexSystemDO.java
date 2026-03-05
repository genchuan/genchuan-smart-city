package cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexsystem;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 指标体系 DO
 *
 * @author 亘川智城
 */
@TableName("eval_index_system")
@KeySequence("eval_index_system_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndexSystemDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 指标体系ID（UUID）
     */
    private String systemId;
    /**
     * 体系名称
     */
    private String name;
    /**
     * 体系编码
     */
    private String code;
    /**
     * 适用对象类型ID（关联sys_object_type.type_id）
     */
    private String objectTypeId;
    /**
     * 版本号
     */
    private String version;
    /**
     * 描述信息
     */
    private String desc;
    /**
     * 分类总数
     */
    private Integer categoryCount;
    /**
     * 指标项总数
     */
    private Integer itemCount;
    /**
     * 状态ID（关联sys_status.status_id）
     */
    private Integer statusId;
    /**
     * 最近使用时间
     */
    private LocalDateTime lastUseTime;
    /**
     * 使用次数
     */
    private Integer useCount;
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