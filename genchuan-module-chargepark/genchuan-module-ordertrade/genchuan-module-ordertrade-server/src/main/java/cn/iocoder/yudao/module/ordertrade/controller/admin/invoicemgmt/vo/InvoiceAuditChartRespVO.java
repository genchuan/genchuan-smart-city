package cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 开票审核统计 Response VO")
@Data
public class InvoiceAuditChartRespVO {

    @Schema(description = "审核量趋势数据（折线图）")
    private List<Map<String, Object>> trendData;

    @Schema(description = "待审核数")
    private Long pendingCount;

    @Schema(description = "审核通过率（%）")
    private BigDecimal approveRate;
}
