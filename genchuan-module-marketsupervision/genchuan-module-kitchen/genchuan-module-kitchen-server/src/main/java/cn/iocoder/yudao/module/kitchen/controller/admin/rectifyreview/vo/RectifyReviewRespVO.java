package cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 整改通知书复审台账 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RectifyReviewRespVO {

    @Schema(description = "[主键ID] 自增主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "14304")
    @ExcelProperty("[主键ID] 自增主键")
    private Long id;

    @Schema(description = "整改通知书ID")
    @ExcelProperty("整改通知书ID")
    private Long rectifyNoticeId;

    @Schema(description = "[台账编号] 整改通知书复审台账唯一编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[台账编号] 整改通知书复审台账唯一编号")
    private String ledgerCode;

    @Schema(description = "[企业ID] 关联park_enterprise_info.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "4078")
    @ExcelProperty("[企业ID] 关联park_enterprise_info.id")
    private Long entId;

    @Schema(description = "[违规类型ID] 关联park_illegal_type_dict.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "16642")
    @ExcelProperty("[违规类型ID] 关联park_illegal_type_dict.id")
    private Long illegalTypeId;

    @Schema(description = "[违规等级ID] 关联park_illegal_level_dict.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "17261")
    @ExcelProperty("[违规等级ID] 关联park_illegal_level_dict.id")
    private Long illegalLevelId;

    @Schema(description = "[违规证据链接] 多链接以英文逗号分隔，varchar类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.iocoder.cn")
    @ExcelProperty("[违规证据链接] 多链接以英文逗号分隔，varchar类型")
    private String evidenceUrl;

    @Schema(description = "[草拟时间] 整改通知书草拟时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[草拟时间] 整改通知书草拟时间")
    private LocalDateTime draftTime;

    @Schema(description = "[复审状态] 如：待复审/已下发/已撤销", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("[复审状态] 如：待复审/已下发/已撤销")
    private String reviewStatus;

    @Schema(description = "[复审人ID] 关联park_user.id")
    @ExcelProperty("[复审人ID] 关联park_user.id")
    private Long reviewBy;

    @Schema(description = "[复审时间] 实际复审操作时间")
    @ExcelProperty("[复审时间] 实际复审操作时间")
    private LocalDateTime reviewTime;

    @Schema(description = "[撤销时间] 仅当状态为已撤销时有值")
    @ExcelProperty("[撤销时间] 仅当状态为已撤销时有值")
    private LocalDateTime cancelTime;

    @Schema(description = "[撤销原因ID] 关联park_cancel_reason_dict.id，仅已撤销状态赋值", example = "30221")
    @ExcelProperty("[撤销原因ID] 关联park_cancel_reason_dict.id，仅已撤销状态赋值")
    private Long cancelReasonId;

    @Schema(description = "[执法复审台账编号] 关联park_law_review_ledger.ledger_code")
    @ExcelProperty("[执法复审台账编号] 关联park_law_review_ledger.ledger_code")
    private String lawLedgerCode;

    @Schema(description = "整改通知书编号")
    @ExcelProperty("整改通知书编号")
    private String rectifyNoticeCode;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[通用扩展字段1] 预留")
    @ExcelProperty("[通用扩展字段1] 预留")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 预留")
    @ExcelProperty("[通用扩展字段2] 预留")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 预留")
    @ExcelProperty("[通用扩展字段3] 预留")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 预留")
    @ExcelProperty("[通用扩展字段4] 预留")
    private String extCommon4;

}
