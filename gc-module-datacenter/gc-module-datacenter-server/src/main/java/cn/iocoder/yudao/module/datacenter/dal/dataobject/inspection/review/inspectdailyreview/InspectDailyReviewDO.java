package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.review.inspectdailyreview;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检日常结果复核 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_daily_review")
@KeySequence("biz_inspect_daily_review_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectDailyReviewDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 复核ID
     */
    private String dailyReviewId;
    /**
     * 复核编码
     */
    private String dailyReviewCode;
    /**
     * 关联日常记录ID
     */
    private String dailyExecId;
    /**
     * 关联日常记录编码
     */
    private String dailyExecCode;
    /**
     * 巡查人员ID
     */
    private String execUserId;
    /**
     * 巡查人员姓名
     */
    private String execUserName;
    /**
     * 巡查区域名称
     */
    private String execAreaName;
    /**
     * 复核内容
     */
    private String reviewContent;
    /**
     * 复核结果
     */
    private String reviewResult;
    /**
     * 复核意见
     */
    private String reviewOpinion;
    /**
     * 复核依据
     */
    private String reviewBasis;
    /**
     * 复核人
     */
    private String reviewUser;
    /**
     * 复核时间
     */
    private LocalDateTime reviewTime;
    /**
     * 整改要求
     */
    private String rectifyRequirement;
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