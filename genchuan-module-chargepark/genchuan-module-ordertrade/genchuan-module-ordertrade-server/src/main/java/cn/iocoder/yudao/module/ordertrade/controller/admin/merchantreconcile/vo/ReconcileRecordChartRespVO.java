package cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 对账记录统计图表 Response VO")
@Data
public class ReconcileRecordChartRespVO {

    @Schema(description = "对账记录趋势数据（折线图）")
    private List<Map<String, Object>> trendData;

    @Schema(description = "不匹配记录数量")
    private Long unmatchedCount;

    @Schema(description = "总记录数量")
    private Long totalCount;

    @Schema(description = "匹配率(%)")
    private BigDecimal matchRate;
}
