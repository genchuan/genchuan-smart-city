package cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 整改通知书复审台账分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RectifyReviewPageReqVO extends PageParam {

    @Schema(description = "[台账编号] 整改通知书复审台账唯一编号")
    private String ledgerCode;

    @Schema(description = "[企业ID] 关联park_enterprise_info.id", example = "4078")
    private Long entId;

    @Schema(description = "[违规类型ID] 关联park_illegal_type_dict.id", example = "16642")
    private Long illegalTypeId;

    @Schema(description = "[违规等级ID] 关联park_illegal_level_dict.id", example = "17261")
    private Long illegalLevelId;

    @Schema(description = "[违规证据链接] 多链接以英文逗号分隔，varchar类型", example = "https://www.iocoder.cn")
    private String evidenceUrl;

    @Schema(description = "[草拟时间] 整改通知书草拟时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] draftTime;

    @Schema(description = "[复审状态] 如：待复审/已下发/已撤销", example = "2")
    private String reviewStatus;

    @Schema(description = "[复审人ID] 关联park_user.id")
    private Long reviewBy;

    @Schema(description = "[复审时间] 实际复审操作时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] reviewTime;

    @Schema(description = "[撤销时间] 仅当状态为已撤销时有值")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] cancelTime;

    @Schema(description = "[撤销原因ID] 关联park_cancel_reason_dict.id，仅已撤销状态赋值", example = "30221")
    private Long cancelReasonId;

    @Schema(description = "[执法复审台账编号] 关联park_law_review_ledger.ledger_code")
    private String lawLedgerCode;
    @Schema(description = "整改通知书编号")
    private String rectifyNoticeCode;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[通用扩展字段1] 预留")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 预留")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 预留")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 预留")
    private String extCommon4;

}
