package cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 金额核算统计 Response VO
 * @author genchuan
 */
@Schema(description = "管理后台 - 金额核算统计 Response VO")
@Data
public class AmountCheckChartRespVO {

    @Schema(description = "核算量趋势数据（折线图）")
    private List<Map<String,Object>> trendData;

    @Schema(description = "核算总数（卡片）")
    private Integer totalCheckCount;

    @Schema(description = "核算准确率（%）（卡片）")
    private BigDecimal checkAccuracy;
}
