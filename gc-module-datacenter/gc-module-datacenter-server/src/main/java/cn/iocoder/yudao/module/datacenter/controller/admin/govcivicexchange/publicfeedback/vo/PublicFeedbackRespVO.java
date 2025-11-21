package cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicfeedback.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 意见反馈 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PublicFeedbackRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8118")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "反馈ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "20093")
    @ExcelProperty("反馈ID，唯一编码，UUID生成")
    private String feedbackId;

    @Schema(description = "反馈单号，格式“FK+YYYYMMDD+6位流水号”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("反馈单号，格式“FK+YYYYMMDD+6位流水号”")
    private String feedbackNo;

    @Schema(description = "反馈类型编码，类型（如“功能问题”“优化建议”），字典表（sys_dict）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("反馈类型编码，类型（如“功能问题”“优化建议”），字典表（sys_dict）")
    private String feedbackTypeCode;

    @Schema(description = "反馈类型名称，与类型编码同步，字典表（sys_dict）", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("反馈类型名称，与类型编码同步，字典表（sys_dict）")
    private String feedbackTypeName;

    @Schema(description = "反馈内容，市民反馈的问题或建议", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("反馈内容，市民反馈的问题或建议")
    private String feedbackContent;

    @Schema(description = "上传图片路径，问题截图，多个用逗号分隔")
    @ExcelProperty("上传图片路径，问题截图，多个用逗号分隔")
    private String imgPath;

    @Schema(description = "反馈人ID，登录用户ID（未登录为空），用户表（sys_user）", example = "25194")
    @ExcelProperty("反馈人ID，登录用户ID（未登录为空），用户表（sys_user）")
    private Long feedbackUserId;

    @Schema(description = "反馈人姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("反馈人姓名")
    private String feedbackUserName;

    @Schema(description = "反馈人电话，加密存储（便于回访）")
    @ExcelProperty("反馈人电话，加密存储（便于回访）")
    private String feedbackUserPhone;

    @Schema(description = "评估状态：1=待评估，2=已采纳，3=不采纳，评估结果", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("评估状态：1=待评估，2=已采纳，3=不采纳，评估结果")
    private Integer evalStatus;

    @Schema(description = "评估意见，管理员评估说明，如“采纳，下版本优化”")
    @ExcelProperty("评估意见，管理员评估说明，如“采纳，下版本优化”")
    private String evalOpinion;

    @Schema(description = "评估人ID，评估管理员ID，用户表（sys_user）", example = "12310")
    @ExcelProperty("评估人ID，评估管理员ID，用户表（sys_user）")
    private Long evalUserId;

    @Schema(description = "评估时间")
    @ExcelProperty("评估时间")
    private LocalDateTime evalTime;

    @Schema(description = "反馈时间，市民提交时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("反馈时间，市民提交时间")
    private LocalDateTime feedbackTime;

    @Schema(description = "备注，补充说明，如“已联系用户回访”", example = "你猜")
    @ExcelProperty("备注，补充说明，如“已联系用户回访”")
    private String remark;

    @Schema(description = "分类扩展字段1，预留，标识反馈模块，如投诉/咨询")
    @ExcelProperty("分类扩展字段1，预留，标识反馈模块，如投诉/咨询")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，标识紧急程度，如普通/紧急")
    @ExcelProperty("分类扩展字段2，预留，标识紧急程度，如普通/紧急")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储优化版本号")
    @ExcelProperty("通用扩展字段1，预留，存储优化版本号")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储回访结果")
    @ExcelProperty("通用扩展字段2，预留，存储回访结果")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}