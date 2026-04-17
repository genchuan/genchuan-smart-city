package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 意见建议统计图表 Response VO")
@Data
public class SuggestionChartRespVO {

    @Schema(description = "意见量趋势数据(折线图渲染)")
    private List<Map<String, Object>> suggestionTrendList;

    @Schema(description = "待处理数,待处理状态的意见总数")
    private Integer waitHandleCount;

    @Schema(description = "处理完成率,已完成 / 总数")
    private BigDecimal handleFinishRate;

}
