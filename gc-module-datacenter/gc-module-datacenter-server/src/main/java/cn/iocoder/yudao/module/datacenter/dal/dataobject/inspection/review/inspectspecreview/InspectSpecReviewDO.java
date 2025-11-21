package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.review.inspectspecreview;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检专项结果复核 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_spec_review")
@KeySequence("biz_inspect_spec_review_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectSpecReviewDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 复核ID
     */
    private String specReviewId;
    /**
     * 复核编码
     */
    private String specReviewCode;
    /**
     * 关联专项记录ID
     */
    private String specExecId;
    /**
     * 关联专项记录编码
     */
    private String specExecCode;
    /**
     * 关联专项计划ID
     */
    private String specPlanId;
    /**
     * 关联专项计划名称
     */
    private String specPlanName;
    /**
     * 巡查人员ID
     */
    private String execUserId;
    /**
     * 巡查人员姓名
     */
    private String execUserName;
    /**
     * 专项主题
     */
    private String specTopic;
    /**
     * 复核重点
     */
    private String reviewFocus;
    /**
     * 复核结果
     */
    private String reviewResult;
    /**
     * 复核意见
     */
    private String reviewOpinion;
    /**
     * 复核人
     */
    private String reviewUser;
    /**
     * 复核时间
     */
    private LocalDateTime reviewTime;
    /**
     * 重查要求
     */
    private String reckRequirement;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建时间
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