package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 逃费记录统计 Response VO
 * @author genchuan
 */
@Schema(description = "管理后台 - 逃费记录统计 Response VO")
@Data
public class DebtRecordChartRespVO {

    @Schema(description = "逃费记录趋势数据（折线图）")
    private List<Map<String,Object>> trendData;

    @Schema(description = "逃费总金额（卡片）")
    private BigDecimal totalArrearAmount;

    @Schema(description = "追缴完成率（%）（卡片）")
    private BigDecimal collectCompleteRate;
}
