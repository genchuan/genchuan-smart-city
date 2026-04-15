package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 欠费记录统计 Response VO
 * @author genchuan
 */
@Schema(description = "管理后台 - 欠费记录统计 Response VO")
@Data
public class ArrearRecordChartRespVO {

    @Schema(description = "欠费金额趋势数据（折线图）")
    private List<Map<String,Object>> trendData;

    @Schema(description = "欠费总金额（卡片）")
    private BigDecimal totalArrearAmount;

    @Schema(description = "结清率（%）（卡片）")
    private BigDecimal clearRate;
}
