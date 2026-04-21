package cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 钱包管理统计 Response VO")
@Data
public class PayWalletChartRespVO {

    @Schema(description = "总余额（分）")
    private Long totalBalance;

    @Schema(description = "今日充值金额（分）")
    private Long todayRechargeAmount;
}
