package cn.iocoder.yudao.module.park.controller.admin.park.statrpt.chargeabnormal.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 收费异常统计结果 Resp VO")
@Data
public class StatReportRespVO {

    @Schema(description = "[异常总金额] 统计周期内异常金额汇总", example = "12345.67")
    private BigDecimal totalAbnormalAmount;

    @Schema(description = "[异常订单数] 统计周期内异常订单数量", example = "128")
    private Long abnormalOrderCount;

    @Schema(description = "[处置完成率] 已处置异常 / 异常总数，单位：百分比", example = "85.50")
    private BigDecimal disposalCompletionRate;

    @Schema(description = "[纠错成功率] 已纠错异常 / 已处置异常，单位：百分比", example = "72.30")
    private BigDecimal correctionSuccessRate;

}
