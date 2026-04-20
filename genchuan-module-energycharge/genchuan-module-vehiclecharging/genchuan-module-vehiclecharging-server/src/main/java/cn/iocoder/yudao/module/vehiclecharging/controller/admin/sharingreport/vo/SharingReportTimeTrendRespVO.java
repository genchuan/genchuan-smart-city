package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SharingReportTimeTrendRespVO {
    private List<TimeTrendData> list;
    @Data
    public static class TimeTrendData {
        private String date;
        private BigDecimal sharingAmount;
        private Integer billCount;
    }
}
