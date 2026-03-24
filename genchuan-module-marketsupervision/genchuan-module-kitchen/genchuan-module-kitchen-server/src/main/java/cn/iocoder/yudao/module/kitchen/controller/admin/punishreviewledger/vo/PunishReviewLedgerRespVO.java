package cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;


@Schema(description = "管理后台 - 处罚通知书复审台账 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PunishReviewLedgerRespVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "7238")
    @ExcelProperty("[主键ID]")
    private Long id;

    @Schema(description = "[台账编号] 唯一编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[台账编号] 唯一编号")
    private String ledgerCode;

    @Schema(description = "逾期标识")
    @ExcelProperty("逾期标识")
    private Integer overdueFlag;


    @Schema(description = "缴费截止时间")
    @ExcelProperty("缴费截止时间")
    private LocalDateTime paymentDeadlineTime;

    @Schema(description = "[企业ID] 关联enterprise_info.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "32251")
    @ExcelProperty("[企业ID] 关联enterprise_info.id")
    private Long entId;

    @Schema(description = "[企业名称] 企业名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[企业名称] 企业名称")
    private String entName;

    @Schema(description = "[违规类型ID] 关联illegal_type_dict.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "28353")
    @ExcelProperty("[违规类型ID] 关联illegal_type_dict.id")
    private Long illegalTypeId;

    @Schema(description = "[违规等级ID] 关联illegal_level_dict.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "20209")
    @ExcelProperty("[违规等级ID] 关联illegal_level_dict.id")
    private Long illegalLevelId;

    @Schema(description = "[企业整改记录id]", example = "4516")
    @ExcelProperty("[企业整改记录id]")
    private Long entRectifyRecordId;

    @Schema(description = "[处罚通知书id]", example = "31157")
    @ExcelProperty("[处罚通知书id]")
    private Long punishNoticeId;

    @Schema(description = "[执法复审台账编号] 关联law_review_ledger.ledger_code")
    @ExcelProperty("[执法复审台账编号] 关联law_review_ledger.ledger_code")
    private String lawLedgerCode;

    @Schema(description = "[违规证据链接] JSON字符串格式，可多链接", example = "https://www.iocoder.cn")
    @ExcelProperty("[违规证据链接] JSON字符串格式，可多链接")
    private String evidenceUrl;

    @Schema(description = "[草拟处罚金额] 单位：元", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[草拟处罚金额] 单位：元")
    private BigDecimal draftPunishAmt;

    @Schema(description = "[处罚法律依据]", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[处罚法律依据]")
    private String legalBasis;

    @Schema(description = "[复审状态] 如：待复审/已下发/已撤销", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("[复审状态] 如：待复审/已下发/已撤销")
    private String reviewStatus;

    @Schema(description = "[复审人] 关联park_user.id")
    @ExcelProperty("[复审人] 关联park_user.id")
    private Long reviewBy;

    @Schema(description = "[撤销原因ID] 关联cancel_reason_dict.id，仅已撤销状态赋值", example = "15547")
    @ExcelProperty("[撤销原因ID] 关联cancel_reason_dict.id，仅已撤销状态赋值")
    private Long cancelReasonId;

    @Schema(description = "[草拟时间]", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[草拟时间]")
    private LocalDateTime draftTime;

    @Schema(description = "[复审时间]")
    @ExcelProperty("[复审时间]")
    private LocalDateTime reviewTime;

    @Schema(description = "[撤销时间]")
    @ExcelProperty("[撤销时间]")
    private LocalDateTime cancelTime;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[通用扩展字段1]")
    @ExcelProperty("[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    @ExcelProperty("[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    @ExcelProperty("[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    @ExcelProperty("[通用扩展字段4]")
    private String extCommon4;

}
