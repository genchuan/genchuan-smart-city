package cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.chart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "汽车充电 - 结算单图表统计 Request VO")
public class SettlementBillChartReqVO {
    @Schema(description = "时间范围开始（时间戳）", example = "1735689600")
    private String timeRangeStart;

    @Schema(description = "时间范围结束（时间戳）", example = "1743494400")
    private String timeRangeEnd;
}
