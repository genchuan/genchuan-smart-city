package cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Schema(description = "管理后台 - 结算单统计总览 Response VO")
public class SettlementBillSummaryRespVO {
    @Schema(description = "结算单总数", example = "32")
    private Integer totalBillCount;

    @Schema(description = "待审核数量", example = "2")
    private Integer pendingAuditCount;

    @Schema(description = "已完成数量", example = "28")
    private Integer completedCount;

    @Schema(description = "总结算金额", example = "456890.25")
    private BigDecimal totalSettlementAmount;

    @Schema(description = "每日趋势折线数据")
    private List<LineItem> lineData;

    @Schema(description = "合作方柱状图数据")
    private List<BarItem> barData;

    @Data
    public static class LineItem {
        private String date;
        private Integer count;
    }

    @Data
    public static class BarItem {
        private String name;
        private BigDecimal amount;
    }
}
