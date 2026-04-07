package cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.chart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "管理后台 - 每日趋势 Request VO")
public class SettlementBillDailyTrendReqVO {
    private String timeRangeStart;
    private String timeRangeEnd;
}
