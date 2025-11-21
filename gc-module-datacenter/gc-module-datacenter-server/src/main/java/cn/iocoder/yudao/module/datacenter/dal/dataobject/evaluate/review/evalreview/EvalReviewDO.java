package cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.review.evalreview;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 复核办理 DO
 *
 * @author zcq
 */
@TableName("sys_eval_review")
@KeySequence("sys_eval_review_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalReviewDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 复核ID
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
     * 关联任务ID
     */
    private String evalTaskId;
    /**
     * 评价对象ID
     */
    private String evalObjectId;
    /**
     * 评价对象名称
     */
    private String evalObjectName;
    /**
     * 复核人员ID
     */
    private String reviewUserId;
    /**
     * 复核人员姓名
     */
    private String reviewUserName;
    /**
     * 复核意见
     */
    private String reviewOpinion;
    /**
     * 核查材料
     */
    private String reviewMats;
    /**
     * 复核状态
     */
    private String reviewStatus;
    /**
     * 分配时间
     */
    private LocalDateTime assignTime;
    /**
     * 复核完成时间
     */
    private LocalDateTime reviewCompleteTime;
    /**
     * 分配人ID
     */
    private String assignUserId;
    /**
     * 分配人姓名
     */
    private String assignUserName;
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