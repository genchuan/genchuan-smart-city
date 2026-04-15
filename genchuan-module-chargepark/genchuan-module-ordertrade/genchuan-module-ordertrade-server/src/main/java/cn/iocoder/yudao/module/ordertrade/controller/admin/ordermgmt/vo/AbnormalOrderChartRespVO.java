package cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 异常订单统计 Response VO
 * @author genchuan
 */
@Schema(description = "管理后台 - 异常订单统计 Response VO")
@Data
public class AbnormalOrderChartRespVO {

    @Schema(description = "异常订单趋势数据（折线图）")
    private List<Map<String,Object>> trendData;

    @Schema(description = "异常类型分布数据（柱状图）")
    private List<Map<String,Object>> typeData;

    @Schema(description = "待处理数（卡片）")
    private Integer waitProcessCount;

    @Schema(description = "处置完成率（%）（卡片）")
    private BigDecimal processCompleteRate;
}
