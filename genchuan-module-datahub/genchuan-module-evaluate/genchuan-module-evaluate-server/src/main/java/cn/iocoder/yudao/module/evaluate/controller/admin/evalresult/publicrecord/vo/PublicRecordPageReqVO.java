package cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.publicrecord.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 评价结果公示分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PublicRecordPageReqVO extends PageParam {

    @Schema(description = "公示UUID（主键，UUID）", example = "11928")
    private String publicId;

    @Schema(description = "公示编号")
    private String code;

    @Schema(description = "关联审核记录ID（关联eval_audit_record.audit_id）", example = "362")
    private String auditId;

    @Schema(description = "评价对象ID（关联eval_object.object_id）", example = "18218")
    private String objectId;

    @Schema(description = "评价标准ID（关联eval_standard_item.standard_item_id）", example = "6344")
    private String standardId;

    @Schema(description = "公示开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] startTime;

    @Schema(description = "公示结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] endTime;

    @Schema(description = "公示状态（关联sys_public_status.status_id）", example = "2")
    private String status;

    @Schema(description = "公示创建人（关联sys_user.user_id）")
    private String createBy;

    @Schema(description = "业务创建时间（创建时间）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bizCreateTime;

    @Schema(description = "公示链接", example = "https://www.iocoder.cn")
    private String publicUrl;

    @Schema(description = "异议数量", example = "5537")
    private Integer objectionCount;

    @Schema(description = "公示完成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] completeTime;

    @Schema(description = "关联申诉记录编号")
    private String appealRecordCode;

    @Schema(description = "公示剩余时长（小时）")
    private BigDecimal remainHour;

    @Schema(description = "公示访问量", example = "4399")
    private Long visitCount;

    @Schema(description = "最新异议时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] latestObjectionTime;

    @Schema(description = "公示发布渠道")
    private String publishChannel;

    @Schema(description = "管理员联系方式")
    private String adminContact;

    @Schema(description = "终止原因", example = "不好")
    private String stopReason;

    @Schema(description = "终止时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] stopTime;

    @Schema(description = "终止人（关联sys_user.user_id）")
    private String stopBy;

    @Schema(description = "公示终止时剩余时长（小时）")
    private BigDecimal stopRemainHour;

    @Schema(description = "终止前异议数量", example = "14256")
    private Integer stopObjectionCount;

    @Schema(description = "后续处理建议")
    private String followSuggest;

    @Schema(description = "历史公示链接状态（已失效/可查看）", example = "1")
    private String urlStatus;

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