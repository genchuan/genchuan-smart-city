package cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.chart;

import lombok.Data;

@Data
public class SettlementBillCountRespVO {
    private StatusCountItem statusCount;

    @Data
    public static class StatusCountItem {
        private Integer pendingAudit;
        private Integer auditPass;
        private Integer settling;
        private Integer completed;
        private Integer rejected;
    }
}
