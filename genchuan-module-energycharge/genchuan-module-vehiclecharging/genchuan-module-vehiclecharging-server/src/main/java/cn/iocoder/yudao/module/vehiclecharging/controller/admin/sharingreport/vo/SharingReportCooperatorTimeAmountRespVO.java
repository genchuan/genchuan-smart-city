package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class SharingReportCooperatorTimeAmountRespVO {
    private List<CooperatorTimeData> list;
    @Data
    public static class CooperatorTimeData {
        private String date;
        private Map<String, BigDecimal> cooperatorData;
    }
}
