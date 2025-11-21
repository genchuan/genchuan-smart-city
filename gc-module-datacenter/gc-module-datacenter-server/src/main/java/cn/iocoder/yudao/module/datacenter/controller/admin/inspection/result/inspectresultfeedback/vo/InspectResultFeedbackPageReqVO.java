package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresultfeedback.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 巡查巡检结果反馈分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InspectResultFeedbackPageReqVO extends PageParam {

    @Schema(description = "反馈ID")
    private String feedbackId;

    @Schema(description = "反馈编码")
    private String feedbackCode;

    @Schema(description = "关联公示ID")
    private String publicId;

    @Schema(description = "关联公示标题")
    private String publicTitle;

    @Schema(description = "反馈人姓名")
    private String feedbackUserName;

    @Schema(description = "反馈人手机号")
    private String feedbackUserPhone;

    @Schema(description = "反馈内容")
    private String feedbackContent;

    @Schema(description = "反馈照片URLs")
    private String feedbackPhotoUrls;

    @Schema(description = "反馈时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] feedbackTime;

    @Schema(description = "反馈状态")
    private String feedbackStatus;

    @Schema(description = "核实人员ID")
    private String verifyUserId;

    @Schema(description = "核实人员姓名")
    private String verifyUserName;

    @Schema(description = "核实结果")
    private String verifyResult;

    @Schema(description = "核实意见")
    private String verifyOpinion;

    @Schema(description = "关联问题ID")
    private String problemId;

    @Schema(description = "处理结果")
    private String handleResult;

    @Schema(description = "处理人")
    private String handleUser;

    @Schema(description = "处理时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] handleTime;

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