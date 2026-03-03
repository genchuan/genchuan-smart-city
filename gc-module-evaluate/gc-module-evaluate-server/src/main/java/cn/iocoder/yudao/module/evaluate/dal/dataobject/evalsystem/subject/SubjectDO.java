package cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.subject;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 评价主体 DO
 *
 * @author 芋道源码
 */
@TableName("eval_subject")
@KeySequence("eval_subject_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 评价主体ID（UUID）
     */
    private String subjectId;
    /**
     * 主体名称
     */
    private String name;
    /**
     * 主体编码
     */
    private String code;
    /**
     * 主体类型ID（关联sys_subject_type.type_id）
     */
    private String subjectTypeId;
    /**
     * 联系人ID（关联sys_user.user_id）
     */
    private String contactId;
    /**
     * 成员数量
     */
    private Integer memberCount;
    /**
     * 使用次数
     */
    private Integer useCount;
    /**
     * 状态ID（关联sys_status.status_id）
     */
    private Integer statusId;
    /**
     * 创建人ID（关联sys_user.user_id）
     */
    private String createBy;
    /**
     * 更新人ID（关联sys_user.user_id）
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
