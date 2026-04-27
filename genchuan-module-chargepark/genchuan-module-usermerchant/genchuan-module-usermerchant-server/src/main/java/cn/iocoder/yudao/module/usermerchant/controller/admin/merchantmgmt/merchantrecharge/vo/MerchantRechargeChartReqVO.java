package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantrecharge.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 商户充值统计 Request VO")
@Data
public class MerchantRechargeChartReqVO {

    @Schema(description = "统计时间范围，支持日/周/月/年维度", example = "2025-03")
    private String timeRange;
}