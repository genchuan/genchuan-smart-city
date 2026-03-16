package cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.appealrecord.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 申诉复核分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppealRecordPageReqVO extends PageParam {

    @Schema(description = "申诉UUID（主键，UUID）", example = "29703")
    private String appealId;

    @Schema(description = "申诉编号")
    private String code;

    @Schema(description = "申诉对象ID（关联eval_object.object_id）", example = "2125")
    private String objectId;

    @Schema(description = "关联公示记录ID（关联eval_public_record.public_id）", example = "17737")
    private String publicId;

    @Schema(description = "关联审核记录ID（关联eval_audit_record.audit_id）", example = "19206")
    private String auditId;

    @Schema(description = "申诉人（关联sys_user.user_id）")
    private String appealBy;

    @Schema(description = "申诉提交时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] submitTime;

    @Schema(description = "复核状态（关联sys_appeal_status.status_id）", example = "2")
    private String status;

    @Schema(description = "原评价得分")
    private BigDecimal originalScore;

    @Schema(description = "复核人员ID（关联sys_user.user_id，多个用逗号分隔）")
    private String reviewBy;

    @Schema(description = "复核完成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] reviewTime;

    @Schema(description = "最终复核结果（维持原结果/修正结果）")
    private String finalResult;

    @Schema(description = "修正后得分")
    private BigDecimal correctScore;

    @Schema(description = "修正后标准ID（关联eval_standard_item.standard_item_id）", example = "16415")
    private String correctStandardId;

    @Schema(description = "结案状态（未结案/已结案）", example = "2")
    private String closeStatus;

    @Schema(description = "结案人（关联sys_user.user_id）")
    private String closeBy;

    @Schema(description = "结案时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] closeTime;

    @Schema(description = "申诉理由摘要", example = "不对")
    private String appealReason;

    @Schema(description = "证明材料数量", example = "5339")
    private Integer fileCount;

    @Schema(description = "待受理时长（小时）")
    private BigDecimal waitHour;

    @Schema(description = "申诉类型", example = "2")
    private String appealType;

    @Schema(description = "原评价等级")
    private String originalGrade;

    @Schema(description = "材料审核状态", example = "2")
    private String fileCheckStatus;

    @Schema(description = "驳回原因完整内容", example = "不香")
    private String rejectReason;

    @Schema(description = "证明材料审核结果")
    private String fileCheckResult;

    @Schema(description = "驳回通知推送状态（已推送/未推送）", example = "2")
    private String notifyStatus;

    @Schema(description = "受理中时长（小时）")
    private BigDecimal reviewHour;

    @Schema(description = "复核进度")
    private String reviewProgress;

    @Schema(description = "阶段性核查结果")
    private String stageResult;

    @Schema(description = "最新操作时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] latestOperTime;

    @Schema(description = "复核超期预警（正常/超期预警）", example = "2")
    private String warningStatus;

    @Schema(description = "复核初步结果")
    private String preResult;

    @Schema(description = "核查材料数量", example = "13234")
    private Integer checkFileCount;

    @Schema(description = "数据修正建议")
    private String correctSuggest;

    @Schema(description = "结案报告下载链接", example = "https://www.iocoder.cn")
    private String closeReportUrl;

    @Schema(description = "数据同步状态（已同步/未同步/同步中）", example = "2")
    private String dataSyncStatus;

    @Schema(description = "同步完成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] syncTime;

    @Schema(description = "申诉人反馈状态（已反馈/未反馈）", example = "2")
    private String feedbackStatus;

    @Schema(description = "关联存档记录ID（关联eval_archive_record.archive_id）", example = "25199")
    private String archiveId;

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