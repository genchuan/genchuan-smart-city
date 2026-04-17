package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SharingReportTimeCountRespVO {
    private TimeStat timeStat;
    private TotalStat totalStat;
    @Data
    public static class TimeStat {
        private Integer dailyCount;
        private Integer weeklyCount;
        private Integer monthlyCount;
    }
    @Data
    public static class TotalStat {
        private BigDecimal totalAmount;
        private Integer totalCount;
        private BigDecimal yoy;
        private BigDecimal mom;
    }
}
