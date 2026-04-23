package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 空位推送统计图表 Response VO")
@Data
public class SpacePushChartRespVO {

    @Schema(description = "推送量趋势数据(折线图渲染),包含日期、推送数量")
    private List<Map<String, Object>> pushTrendList;

    @Schema(description = "总推送量")
    private Integer totalPushCount;

    @Schema(description = "推送成功率")
    private BigDecimal pushSuccessRate;

}
