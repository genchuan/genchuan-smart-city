package cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingpayment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 停车缴费态势 Response VO")
@Data
public class ParkingPaymentChartRespVO {

    @Schema(description = "总支付笔数")
    private Integer totalPay;

    @Schema(description = "欠费笔数")
    private Integer arrearsCount;

    @Schema(description = "总收入")
    private BigDecimal totalIncome;

    @Schema(description = "缴费率")
    private BigDecimal payRate;

    @Schema(description = "每日收入趋势列表")
    private List<DayIncomeItem> dayIncomeList;

    @Schema(description = "每日支付笔数趋势列表")
    private List<DayPayItem> dayPayList;

    @Schema(description = "每日收入项")
    @Data
    public static class DayIncomeItem {
        @Schema(description = "日期")
        private String date;
        @Schema(description = "收入金额")
        private BigDecimal income;
    }

    @Schema(description = "每日支付项")
    @Data
    public static class DayPayItem {
        @Schema(description = "日期")
        private String date;
        @Schema(description = "支付笔数")
        private Integer count;
    }

}
