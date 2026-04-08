package cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.chart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "管理后台 - 每日趋势 Response VO")
public class SettlementBillDailyTrendRespVO {
    private List<DailyItem> list;

    @Data
    public static class DailyItem {
        private String date;
        private Integer totalCount;
        private Integer completedCount;
    }
}
