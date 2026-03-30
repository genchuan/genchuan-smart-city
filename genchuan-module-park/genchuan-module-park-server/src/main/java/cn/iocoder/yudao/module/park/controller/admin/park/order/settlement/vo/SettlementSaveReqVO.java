package cn.iocoder.yudao.module.park.controller.admin.park.order.settlement.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 分账结算新增/修改 Request VO")
@Data
public class SettlementSaveReqVO {

    @Schema(description = "[主键ID] 分账结算唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "11504")
    private Long id;

    @Schema(description = "[商户ID] 关联商户ID，park_merchant.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "2335")
    @NotNull(message = "[商户ID] 关联商户ID，park_merchant.id不能为空")
    private Long merchantId;

    @Schema(description = "[车场ID] 关联车场ID，park_lot.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "32608")
    @NotNull(message = "[车场ID] 关联车场ID，park_lot.id不能为空")
    private Long lotId;

    @Schema(description = "[统计周期] 日/周/月", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[统计周期] 日/周/月不能为空")
    private String statCycle;

    @Schema(description = "[统计开始日期] 结算统计开始日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[统计开始日期] 结算统计开始日期不能为空")
    private LocalDate startDate;

    @Schema(description = "[统计结束日期] 结算统计结束日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[统计结束日期] 结算统计结束日期不能为空")
    private LocalDate endDate;

    @Schema(description = "[总交易额] 统计期间总交易额")
    private BigDecimal totalAmount;

    @Schema(description = "[退款金额] 统计期间退款金额")
    private BigDecimal refundAmount;

    @Schema(description = "[平台分成金额] 平台分成金额")
    private BigDecimal platformAmount;

    @Schema(description = "[商户分成金额] 商户分成金额")
    private BigDecimal merchantAmount;

    @Schema(description = "[税费金额] 税费金额")
    private BigDecimal taxAmount;

    @Schema(description = "[结算状态] 待审核/已审核/已支付/已驳回", example = "2")
    private String settlementStatus;

    @Schema(description = "[支付时间] 支付时间，可为 NULL")
    private LocalDateTime payTime;

    @Schema(description = "[支付方式] 支付方式", example = "2")
    private String payType;

    @Schema(description = "[备注] 分账结算相关备注说明", example = "你说的对")
    private String remark;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}
