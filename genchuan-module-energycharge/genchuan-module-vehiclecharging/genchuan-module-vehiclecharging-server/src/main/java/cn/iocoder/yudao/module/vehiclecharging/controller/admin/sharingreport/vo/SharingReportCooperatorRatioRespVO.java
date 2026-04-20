package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SharingReportCooperatorRatioRespVO {
    private List<CooperatorRatioData> list;
    @Data
    public static class CooperatorRatioData {
        private String name;
        private BigDecimal value;
        private BigDecimal amount;
    }
}