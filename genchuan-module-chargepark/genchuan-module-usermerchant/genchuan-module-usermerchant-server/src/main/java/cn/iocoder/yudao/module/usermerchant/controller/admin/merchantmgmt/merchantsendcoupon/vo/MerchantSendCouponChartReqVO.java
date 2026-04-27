package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantsendcoupon.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 商户发券表图表数据 Request VO")
@Data
public class MerchantSendCouponChartReqVO {

    @Schema(description = "时间范围")
    private String timeRange;

}
