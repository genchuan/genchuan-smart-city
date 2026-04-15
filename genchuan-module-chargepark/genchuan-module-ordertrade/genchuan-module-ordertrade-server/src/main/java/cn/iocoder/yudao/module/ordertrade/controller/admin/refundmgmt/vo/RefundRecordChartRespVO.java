package cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 退款记录统计 Response VO
 * @author genchuan
 */
@Schema(description = "管理后台 - 退款记录统计 Response VO")
@Data
public class RefundRecordChartRespVO {

    @Schema(description = "退款金额趋势数据（折线图）")
    private List<Map<String,Object>> trendData;

    @Schema(description = "退款总金额（卡片）")
    private BigDecimal totalRefundAmount;

    @Schema(description = "退款成功率（%）（卡片）")
    private BigDecimal refundSuccessRate;
}
