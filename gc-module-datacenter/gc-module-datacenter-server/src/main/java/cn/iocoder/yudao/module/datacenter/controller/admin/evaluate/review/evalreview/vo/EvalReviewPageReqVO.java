package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalreview.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 复核办理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvalReviewPageReqVO extends PageParam {

    @Schema(description = "复核ID")
    private String reviewId;

    @Schema(description = "关联申诉ID")
    private String appealId;

    @Schema(description = "申诉编号")
    private String appealCode;

    @Schema(description = "关联任务ID")
    private String evalTaskId;

    @Schema(description = "评价对象ID")
    private String evalObjectId;

    @Schema(description = "评价对象名称")
    private String evalObjectName;

    @Schema(description = "复核人员ID")
    private String reviewUserId;

    @Schema(description = "复核人员姓名")
    private String reviewUserName;

    @Schema(description = "复核意见")
    private String reviewOpinion;

    @Schema(description = "核查材料")
    private String reviewMats;

    @Schema(description = "复核状态")
    private String reviewStatus;

    @Schema(description = "分配时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] assignTime;

    @Schema(description = "复核完成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] reviewCompleteTime;

    @Schema(description = "分配人ID")
    private String assignUserId;

    @Schema(description = "分配人姓名")
    private String assignUserName;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}