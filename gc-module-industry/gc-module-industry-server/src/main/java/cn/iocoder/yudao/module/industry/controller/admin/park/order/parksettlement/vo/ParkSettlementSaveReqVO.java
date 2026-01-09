package cn.iocoder.yudao.module.industry.controller.admin.park.order.parksettlement.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 分账结算表新增/修改 Request VO")
@Data
public class ParkSettlementSaveReqVO {

    @Schema(description = "主键ID，自增", requiredMode = Schema.RequiredMode.REQUIRED, example = "23205")
    private Long id;

    @Schema(description = "商户ID，商户唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "506")
    @NotNull(message = "商户ID，商户唯一标识不能为空")
    private Long merchantId;

    @Schema(description = "结算总金额，本次结算的总金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "结算总金额，本次结算的总金额不能为空")
    private BigDecimal totalAmount;

    @Schema(description = "分账比例配置，JSON格式varchar")
    private String splitRatio;

    @Schema(description = "平台分成金额，平台应分得的金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "平台分成金额，平台应分得的金额不能为空")
    private BigDecimal platformAmount;

    @Schema(description = "商户分成金额，商户应分得的金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商户分成金额，商户应分得的金额不能为空")
    private BigDecimal merchantAmount;

    @Schema(description = "税费金额，本次结算产生的税费金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "税费金额，本次结算产生的税费金额不能为空")
    private BigDecimal taxAmount;

    @Schema(description = "结算状态：待生成 / 待审核 / 已结算 / 已驳回", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "结算状态：待生成 / 待审核 / 已结算 / 已驳回不能为空")
    private String settlementStatus;

    @Schema(description = "生成时间，结算数据生成时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "生成时间，结算数据生成时间不能为空")
    private LocalDateTime generateTime;

    @Schema(description = "审核时间，结算审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "审核人，结算审核人")
    private String auditBy;

    @Schema(description = "转账时间，结算转账完成时间")
    private LocalDateTime transferTime;

    @Schema(description = "统计唯一标识码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计唯一标识码不能为空")
    private String statCode;

    @Schema(description = "统计周期标识", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期标识不能为空")
    private String statCycle;

    @Schema(description = "统计周期名称，如：2025年9月", example = "王五")
    private String statCycleName;

    @Schema(description = "统计区间起始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计区间起始时间不能为空")
    private LocalDateTime statStartTime;

    @Schema(description = "统计区间截止时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计区间截止时间不能为空")
    private LocalDateTime statEndTime;

    @Schema(description = "统计时间，实际统计时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计时间，实际统计时间不能为空")
    private LocalDateTime statTime;

    @Schema(description = "备注，业务备注说明", example = "随便")
    private String remark;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}
