package cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.add;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Schema(description = "管理后台 - 处罚通知书复审台账新增 Request VO")
public class AddPunishReviewLedgerReq {

    /**
     * =========================
     * 1. 前端传入参数（用户填写）
     * =========================
     */

    /**
     * 草拟处罚金额
     * 说明：用户填写的处罚金额，单位：元
     */
    @Schema(description = "[草拟处罚金额] 单位：元", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[草拟处罚金额]不能为空")
    private BigDecimal draftPunishAmt;

    /**
     * 处罚法律依据
     * 说明：用户填写的处罚依据（法规/条例等）
     */
    @Schema(description = "[处罚法律依据]", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[处罚法律依据]不能为空")
    private String legalBasis;

    /**
     * 企业整改记录ID
     * 说明：用于关联整改记录，是整个数据链路的入口
     */
    @Schema(description = "[企业整改记录ID]", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[企业整改记录ID]不能为空")
    private Long entRectifyRecordId;


    /**
     * =========================
     * 2. 后端自动补全字段（系统生成）
     * =========================
     */

    /**
     * 台账编号
     * 说明：系统自动生成的唯一编号（如：PNRL20260318001）
     */
    @Schema(hidden = true)
    private String ledgerCode;

    /**
     * 企业ID
     * 说明：从【企业整改记录】中获取
     */
    @Schema(hidden = true)
    private Long entId;

    /**
     * 违规类型ID
     * 说明：整改记录 → 整改通知书 → 整改台账中获取
     */
    @Schema(hidden = true)
    private Long illegalTypeId;

    /**
     * 违规等级ID
     * 说明：整改记录 → 整改通知书 → 整改台账中获取
     */
    @Schema(hidden = true)
    private Long illegalLevelId;

    /**
     * 违规证据URL
     * 说明：整改记录 → 整改通知书 → 整改台账中获取（JSON字符串，可多个）
     */
    @Schema(hidden = true)
    private String evidenceUrl;

    /**
     * 复审状态
     * 说明：系统默认赋值为【待复审】
     */
    @Schema(hidden = true)
    private String reviewStatus;
}
