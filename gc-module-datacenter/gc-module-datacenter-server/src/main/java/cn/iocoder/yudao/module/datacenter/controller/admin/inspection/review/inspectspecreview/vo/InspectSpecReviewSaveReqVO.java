package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectspecreview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 巡查巡检专项结果复核新增/修改 Request VO")
@Data
public class InspectSpecReviewSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "复核ID")
    private String specReviewId;

    @Schema(description = "复核编码")
    private String specReviewCode;

    @Schema(description = "关联专项记录ID")
    private String specExecId;

    @Schema(description = "关联专项记录编码")
    private String specExecCode;

    @Schema(description = "关联专项计划ID")
    private String specPlanId;

    @Schema(description = "关联专项计划名称")
    private String specPlanName;

    @Schema(description = "巡查人员ID")
    private String execUserId;

    @Schema(description = "巡查人员姓名")
    private String execUserName;

    @Schema(description = "专项主题")
    private String specTopic;

    @Schema(description = "复核重点")
    private String reviewFocus;

    @Schema(description = "复核结果")
    private String reviewResult;

    @Schema(description = "复核意见")
    private String reviewOpinion;

    @Schema(description = "复核人")
    private String reviewUser;

    @Schema(description = "复核时间")
    private LocalDateTime reviewTime;

    @Schema(description = "重查要求")
    private String reckRequirement;

    @Schema(description = "创建人")
    private String createUser;

    @Schema(description = "创建时间")
    private LocalDateTime createTimeBiz;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}