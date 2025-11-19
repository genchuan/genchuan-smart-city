package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.review.inspectrectifyreview;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检整改结果复核 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_rectify_review")
@KeySequence("biz_inspect_rectify_review_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectRectifyReviewDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 复核ID
     */
    private String rectifyReviewId;
    /**
     * 复核编码
     */
    private String rectifyReviewCode;
    /**
     * 关联验收ID
     */
    private String acceptId;
    /**
     * 关联验收编码
     */
    private String acceptCode;
    /**
     * 关联整改任务ID
     */
    private String rectifyTaskId;
    /**
     * 关联问题名称
     */
    private String problemName;
    /**
     * 责任部门名称
     */
    private String deptName;
    /**
     * 整改负责人姓名
     */
    private String leaderUserName;
    /**
     * 复核方式
     */
    private String reviewMethod;
    /**
     * 复核照片URLs
     */
    private String reviewPhotoUrls;
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
     * 创建人
     */
    private String createUser;
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