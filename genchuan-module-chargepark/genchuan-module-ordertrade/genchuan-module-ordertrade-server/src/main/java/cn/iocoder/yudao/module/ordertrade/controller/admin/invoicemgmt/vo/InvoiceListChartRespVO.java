package cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 发票列表统计 Response VO")
@Data
public class InvoiceListChartRespVO {

    @Schema(description = "开票量趋势数据（折线图）")
    private List<Map<String, Object>> trendData;

    @Schema(description = "今日开票量")
    private Long todayInvoiceCount;

    @Schema(description = "开票成功率（%）")
    private BigDecimal successRate;
}
