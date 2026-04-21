package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 追缴跟踪统计 Response VO
 * @author genchuan
 */
@Schema(description = "管理后台 - 追缴跟踪统计 Response VO")
@Data
public class CollectTrackChartRespVO {

    @Schema(description = "追缴进度趋势数据（折线图）")
    private List<Map<String,Object>> trendData;

    @Schema(description = "追缴方式分布数据（柱状图）")
    private List<Map<String,Object>> methodData;

    @Schema(description = "待追缴数（卡片）")
    private Integer waitCollectCount;

    @Schema(description = "追缴完成率（%）（卡片）")
    private BigDecimal collectCompleteRate;
}
