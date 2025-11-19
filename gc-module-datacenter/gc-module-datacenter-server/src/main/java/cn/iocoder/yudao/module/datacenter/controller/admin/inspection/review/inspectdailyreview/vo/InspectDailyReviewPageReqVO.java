package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectdailyreview.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 巡查巡检日常结果复核分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InspectDailyReviewPageReqVO extends PageParam {

    @Schema(description = "复核ID")
    private String dailyReviewId;

    @Schema(description = "复核编码")
    private String dailyReviewCode;

    @Schema(description = "关联日常记录ID")
    private String dailyExecId;

    @Schema(description = "关联日常记录编码")
    private String dailyExecCode;

    @Schema(description = "巡查人员ID")
    private String execUserId;

    @Schema(description = "巡查人员姓名")
    private String execUserName;

    @Schema(description = "巡查区域名称")
    private String execAreaName;

    @Schema(description = "复核内容")
    private String reviewContent;

    @Schema(description = "复核结果")
    private String reviewResult;

    @Schema(description = "复核意见")
    private String reviewOpinion;

    @Schema(description = "复核依据")
    private String reviewBasis;

    @Schema(description = "复核人")
    private String reviewUser;

    @Schema(description = "复核时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] reviewTime;

    @Schema(description = "整改要求")
    private String rectifyRequirement;

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