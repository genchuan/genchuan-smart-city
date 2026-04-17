package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SharingReportSummaryRespVO {
    private BigDecimal totalSettlementAmount;
    private BigDecimal totalSharingAmount;
    private Integer totalBillCount;
    private BigDecimal yoyRatio;
    private BigDecimal momRatio;
    private List<LineData> lineData;
    private List<BarData> barData;
    private List<PieData> pieData;

    @Data
    public static class LineData {
        private String date;
        private BigDecimal amount;
    }
    @Data
    public static class BarData {
        private String name;
        private BigDecimal amount;
    }
    @Data
    public static class PieData {
        private String name;
        private BigDecimal amount; // 用于内部计算
        private BigDecimal value; // 占比
    }
}
