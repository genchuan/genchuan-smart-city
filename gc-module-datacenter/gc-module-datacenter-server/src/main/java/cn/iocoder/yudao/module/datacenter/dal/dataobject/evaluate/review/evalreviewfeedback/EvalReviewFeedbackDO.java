package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.review.evalreviewfeedback;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 复核结果反馈 DO
 *
 * @author zcq
 */
@TableName("sys_eval_review_feedback")
@KeySequence("sys_eval_review_feedback_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalReviewFeedbackDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 反馈ID
     */
    private String reviewFeedbackId;
    /**
     * 关联复核ID
     */
    private String reviewId;
    /**
     * 关联申诉ID
     */
    private String appealId;
    /**
     * 申诉编号
     */
    private String appealCode;
    /**
     * 评价对象ID
     */
    private String evalObjectId;
    /**
     * 评价对象名称
     */
    private String evalObjectName;
    /**
     * 原评价等级
     */
    private String originalEvalLevel;
    /**
     * 原评价得分
     */
    private BigDecimal originalEvalScore;
    /**
     * 最终复核结果
     */
    private String finalReviewResult;
    /**
     * 修正后等级
     */
    private String revisedLevel;
    /**
     * 修正后得分
     */
    private BigDecimal revisedScore;
    /**
     * 反馈意见
     */
    private String feedbackOpinion;
    /**
     * 反馈时间
     */
    private LocalDateTime feedbackTime;
    /**
     * 反馈方式
     */
    private String feedbackMethod;
    /**
     * 创建人(业务)
     */
    private String createUserBiz;
    /**
     * 创建时间(业务)
     */
    private LocalDateTime createTimeBiz;
    /**
     * 分类扩展字段1
     */
    private String extCat1;
    /**
     * 分类扩展字段2
     */
    private String extCat2;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;

}