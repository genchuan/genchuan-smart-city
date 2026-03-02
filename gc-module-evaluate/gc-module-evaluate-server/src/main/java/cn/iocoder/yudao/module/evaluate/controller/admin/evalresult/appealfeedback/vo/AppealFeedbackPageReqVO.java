package cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.appealfeedback.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 申诉反馈分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppealFeedbackPageReqVO extends PageParam {

    @Schema(description = "申诉反馈UUID（业务主键）", example = "21518")
    private String feedbackId;

    @Schema(description = "申诉复核ID，关联eval_appeal_review.appeal_id", example = "19741")
    private String appealId;

    @Schema(description = "反馈内容")
    private String feedbackContent;

    @Schema(description = "反馈时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] feedbackTime;

    @Schema(description = "反馈人，关联sys_user.user_id")
    private String feedbackBy;

    @Schema(description = "反馈状态，关联sys_data_status.status_id", example = "1")
    private String status;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}