package cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.subjectmember;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 评价主体成员 DO
 *
 * @author 芋道源码
 */
@TableName("eval_subject_member")
@KeySequence("eval_subject_member_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectMemberDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 评价主体成员ID（UUID）
     */
    private String memberId;
    /**
     * 评价主体ID（关联eval_subject.subject_id）
     */
    private String subjectId;
    /**
     * 成员用户ID（关联sys_user.user_id）
     */
    private String userId;
    /**
     * 加入时间
     */
    private LocalDateTime joinTime;
    /**
     * 退出时间，未退出为空
     */
    private LocalDateTime exitTime;
    /**
     * 状态ID（关联sys_status.status_id）
     */
    private Integer statusId;
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
