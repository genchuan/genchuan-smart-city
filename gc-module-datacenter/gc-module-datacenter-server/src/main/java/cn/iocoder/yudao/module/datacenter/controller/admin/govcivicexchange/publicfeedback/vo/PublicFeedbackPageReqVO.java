package cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicfeedback.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 意见反馈分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PublicFeedbackPageReqVO extends PageParam {

    @Schema(description = "反馈ID，唯一编码，UUID生成", example = "20093")
    private String feedbackId;

    @Schema(description = "反馈单号，格式“FK+YYYYMMDD+6位流水号”")
    private String feedbackNo;

    @Schema(description = "反馈类型编码，类型（如“功能问题”“优化建议”），字典表（sys_dict）")
    private String feedbackTypeCode;

    @Schema(description = "反馈类型名称，与类型编码同步，字典表（sys_dict）", example = "芋艿")
    private String feedbackTypeName;

    @Schema(description = "反馈内容，市民反馈的问题或建议")
    private String feedbackContent;

    @Schema(description = "上传图片路径，问题截图，多个用逗号分隔")
    private String imgPath;

    @Schema(description = "反馈人ID，登录用户ID（未登录为空），用户表（sys_user）", example = "25194")
    private Long feedbackUserId;

    @Schema(description = "反馈人姓名", example = "张三")
    private String feedbackUserName;

    @Schema(description = "反馈人电话，加密存储（便于回访）")
    private String feedbackUserPhone;

    @Schema(description = "评估状态：1=待评估，2=已采纳，3=不采纳，评估结果", example = "2")
    private Integer evalStatus;

    @Schema(description = "评估意见，管理员评估说明，如“采纳，下版本优化”")
    private String evalOpinion;

    @Schema(description = "评估人ID，评估管理员ID，用户表（sys_user）", example = "12310")
    private Long evalUserId;

    @Schema(description = "评估时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] evalTime;

    @Schema(description = "反馈时间，市民提交时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] feedbackTime;

    @Schema(description = "备注，补充说明，如“已联系用户回访”", example = "你猜")
    private String remark;

    @Schema(description = "分类扩展字段1，预留，标识反馈模块，如投诉/咨询")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，标识紧急程度，如普通/紧急")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储优化版本号")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储回访结果")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}