package cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 处罚通知书复审台账分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PunishReviewLedgerPageReqVO extends PageParam {

    @Schema(description = "id列表")
    private List<Long> idList;

    @Schema(description = "[台账编号] 唯一编号")
    private String ledgerCode;

    @Schema(description = "[企业ID] 关联enterprise_info.id", example = "32251")
    private Long entId;

    // 起草时间
    @Schema(description = "起草时间-开始")
    private LocalDateTime draftTimeStart;
    @Schema(description = "起草时间-结束")
    private LocalDateTime draftTimeEnd;

    // 复审时间（XML用到，必须加！）
    @Schema(description = "复审时间-开始")
    private LocalDateTime reviewTimeStart;
    @Schema(description = "复审时间-结束")
    private LocalDateTime reviewTimeEnd;

    // 撤销时间（XML用到，必须加！）
    @Schema(description = "撤销时间-开始")
    private LocalDateTime cancelTimeStart;
    @Schema(description = "撤销时间-结束")
    private LocalDateTime cancelTimeEnd;

    @Schema(description = "[企业名称]", example = "32251")
    private String entName;

    @Schema(description = "[违规类型ID] 关联illegal_type_dict.id", example = "28353")
    private Long illegalTypeId;

    @Schema(description = "[违规等级ID] 关联illegal_level_dict.id", example = "20209")
    private Long illegalLevelId;

    @Schema(description = "[企业整改记录id]", example = "4516")
    private Long entRectifyRecordId;

    @Schema(description = "[处罚通知书id]", example = "31157")
    private Long punishNoticeId;

    @Schema(description = "[执法复审台账编号] 关联law_review_ledger.ledger_code")
    private String lawLedgerCode;

    @Schema(description = "[违规证据链接] JSON字符串格式，可多链接", example = "https://www.iocoder.cn")
    private String evidenceUrl;

    @Schema(description = "[草拟处罚金额] 单位：元")
    private BigDecimal draftPunishAmt;

    @Schema(description = "[处罚法律依据]")
    private String legalBasis;

    @Schema(description = "[复审状态] 如：待复审/已下发/已撤销", example = "1")
    private String reviewStatus;

    @Schema(description = "[复审人] 关联park_user.id")
    private Long reviewBy;

    @Schema(description = "[撤销原因ID] 关联cancel_reason_dict.id，仅已撤销状态赋值", example = "15547")
    private Long cancelReasonId;

    @Schema(description = "[是否逾期] 0/1", example = "1")
    private Integer overdueFlag;


    @Schema(description = "[草拟时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] draftTime;

    @Schema(description = "[复审时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] reviewTime;

    @Schema(description = "[撤销时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] cancelTime;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}
