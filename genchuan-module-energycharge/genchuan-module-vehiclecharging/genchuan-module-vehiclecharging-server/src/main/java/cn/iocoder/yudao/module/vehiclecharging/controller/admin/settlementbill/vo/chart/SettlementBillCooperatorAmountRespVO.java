package cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.chart;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SettlementBillCooperatorAmountRespVO {
    private List<CooperatorItem> list;

    @Data
    public static class CooperatorItem {
        private String name;
        private BigDecimal amount;
        private Integer count;
    }
}
