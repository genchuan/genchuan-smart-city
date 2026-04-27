package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantrecharge.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 商户充值统计响应 VO")
@Data
public class MerchantRechargeChartRespVO {

    @Schema(description = "充值金额趋势数据")
    private List<RechargeAmountTrendVO> rechargeAmountTrend;

    @Schema(description = "充值总金额", example = "230000.00")
    private BigDecimal rechargeAmount;

    @Schema(description = "充值成功率", example = "0.99")
    private BigDecimal rechargeSuccessRate;

    @Schema(description = "充值金额趋势数据")
    @Data
    public static class RechargeAmountTrendVO {
        @Schema(description = "日期", example = "2025-01")
        private String date;
        @Schema(description = "充值金额", example = "50000.00")
        private BigDecimal amount;
    }
}