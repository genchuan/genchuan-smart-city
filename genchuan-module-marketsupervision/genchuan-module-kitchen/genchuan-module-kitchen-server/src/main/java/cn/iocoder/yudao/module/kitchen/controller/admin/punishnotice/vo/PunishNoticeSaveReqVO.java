package cn.iocoder.yudao.module.kitchen.controller.admin.punishnotice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 处罚通知书新增/修改 Request VO")
@Data
public class PunishNoticeSaveReqVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "28922")
    private Long id;

    @Schema(description = "[处罚通知书编号] 唯一编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[处罚通知书编号] 唯一编号不能为空")
    private String noticeCode;

    @Schema(description = "[处罚复审台账ID] 关联punish_review_ledger.id，唯一", requiredMode = Schema.RequiredMode.REQUIRED, example = "30647")
    @NotNull(message = "[处罚复审台账ID] 关联punish_review_ledger.id，唯一不能为空")
    private Long punishReviewId;

    @Schema(description = "[企业缴费记录表id]", example = "5943")
    private Long entPayRecordId;

    @Schema(description = "[下发时间]", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[下发时间]不能为空")
    private LocalDateTime issueTime;

    @Schema(description = "[送达时间]")
    private LocalDateTime receiveTime;

    @Schema(description = "[缴款期限]", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[缴款期限]不能为空")
    private LocalDateTime payDeadline;

    @Schema(description = "[送达状态] 如：未送达/已送达/拒收", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[送达状态] 如：未送达/已送达/拒收不能为空")
    private String receiveStatus;

    @Schema(description = "[实际处罚金额] 单位：元", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[实际处罚金额] 单位：元不能为空")
    private BigDecimal actualPunishAmt;

    @Schema(description = "[处罚决定书原件内容] 富文本", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[处罚决定书原件内容] 富文本不能为空")
    private String decisionContent;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}
