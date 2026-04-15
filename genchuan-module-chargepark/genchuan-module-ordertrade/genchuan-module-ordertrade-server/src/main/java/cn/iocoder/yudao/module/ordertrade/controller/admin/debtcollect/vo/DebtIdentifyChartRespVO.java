package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 逃费识别统计 Response VO
 * @author genchuan
 */
@Schema(description = "管理后台 - 逃费识别统计 Response VO")
@Data
public class DebtIdentifyChartRespVO {

    @Schema(description = "逃费识别趋势数据（折线图）")
    private List<Map<String,Object>> trendData;

    @Schema(description = "各场站逃费数据（柱状图）")
    private List<Map<String,Object>> stationData;

    @Schema(description = "待识别数（卡片）")
    private Integer waitIdentifyCount;

    @Schema(description = "识别成功率（%）（卡片）")
    private BigDecimal identifySuccessRate;
}
