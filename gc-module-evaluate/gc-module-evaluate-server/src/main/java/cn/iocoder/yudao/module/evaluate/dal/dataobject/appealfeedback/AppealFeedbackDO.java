package cn.iocoder.yudao.module.evaluate.dal.dataobject.appealfeedback;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 申诉反馈 DO
 *
 * @author 亘川智城
 */
@TableName("eval_appeal_feedback")
@KeySequence("eval_appeal_feedback_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppealFeedbackDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 申诉反馈UUID（业务主键）
     */
    private String feedbackId;
    /**
     * 申诉复核ID，关联eval_appeal_review.appeal_id
     */
    private String appealId;
    /**
     * 反馈内容
     */
    private String feedbackContent;
    /**
     * 反馈时间
     */
    private LocalDateTime feedbackTime;
    /**
     * 反馈人，关联sys_user.user_id
     */
    private String feedbackBy;
    /**
     * 反馈状态，关联sys_data_status.status_id
     */
    private String status;
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