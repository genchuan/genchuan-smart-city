package cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 退款申请统计 Response VO
 * @author genchuan
 */
@Schema(description = "管理后台 - 退款申请统计 Response VO")
@Data
public class RefundApplyChartRespVO {

    @Schema(description = "退款申请趋势数据（折线图）")
    private List<Map<String,Object>> trendData;

    @Schema(description = "退款类型分布数据（柱状图）")
    private List<Map<String,Object>> typeData;

    @Schema(description = "今日核心指标（卡片）")
    private CardData cardData;

    @Data
    @Schema(description = "卡片指标")
    public static class CardData {

        @Schema(description = "待审核数（卡片）")
        private Integer waitAuditCount;

        @Schema(description = "审核通过率（%）（卡片）")
        private BigDecimal auditPassRate;
    }
}
