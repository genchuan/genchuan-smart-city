package cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 全部订单统计 Response VO
 * @author genchuan
 */
@Schema(description = "管理后台 - 全部订单统计 Response VO")
@Data
public class AllOrderChartRespVO {

    @Schema(description = "订单量趋势数据（折线图）")
    private List<Map<String,Object>> trendData;

    @Schema(description = "订单类型分布数据（柱状图）")
    private List<Map<String,Object>> typeData;

    @Schema(description = "今日订单量")
    private Integer todayOrderCount;

    @Schema(description = "今日营收")
    private BigDecimal todayRevenue;

    @Schema(description = "今日支付率（%）")
    private BigDecimal payRate;
}
