package cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 钱包管理统计 Response VO")
@Data
public class PayWalletChartRespVO {

    @Schema(description = "今日核心指标（卡片）")
    private CardData cardData;

    @Data
    @Schema(description = "卡片指标")
    public static class CardData {

        @Schema(description = "总余额（分）")
        private Long totalBalance;

        @Schema(description = "今日充值金额（分）")
        private Long todayRechargeAmount;
    }
}
