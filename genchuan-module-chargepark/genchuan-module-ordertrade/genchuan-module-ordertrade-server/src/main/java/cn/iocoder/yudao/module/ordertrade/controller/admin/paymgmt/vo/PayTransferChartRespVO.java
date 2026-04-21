package cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 转账订单统计 Response VO")
@Data
public class PayTransferChartRespVO {

    @Schema(description = "转账订单趋势数据（折线图）")
    private List<Map<String, Object>> trendData;

    @Schema(description = "今日转账订单量")
    private Long todayTransferCount;
}
