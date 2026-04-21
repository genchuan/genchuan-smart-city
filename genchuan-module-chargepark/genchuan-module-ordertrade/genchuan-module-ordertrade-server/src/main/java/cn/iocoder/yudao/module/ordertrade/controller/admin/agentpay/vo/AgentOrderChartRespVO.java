package cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 代付订单统计 Response VO")
@Data
public class AgentOrderChartRespVO {

    @Schema(description = "代付订单趋势数据（折线图）")
    private List<Map<String, Object>> trendData;

    @Schema(description = "今日代付订单量")
    private Long todayOrderCount;

    @Schema(description = "今日代付金额")
    private BigDecimal todayAmount;
}
