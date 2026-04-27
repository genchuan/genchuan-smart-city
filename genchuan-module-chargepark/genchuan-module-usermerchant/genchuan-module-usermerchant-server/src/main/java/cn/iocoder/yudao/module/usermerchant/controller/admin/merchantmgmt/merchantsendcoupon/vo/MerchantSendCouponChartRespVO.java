package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantsendcoupon.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 商户发券表图表数据 Response VO")
@Data
public class MerchantSendCouponChartRespVO {

    @Schema(description = "发券量趋势数据")
    private List<MerchantSendCouponChartRespVO.SendCountTrendVO> sendCountTrend;

    @Schema(description = "发券量")
    private Long sendCount;

    @Schema(description = "核销率")
    private BigDecimal useRate;

    @Schema(description = "发券量趋势数据")
    @Data
    public static class SendCountTrendVO {
        private String date;
        private Long count;
    }

}
