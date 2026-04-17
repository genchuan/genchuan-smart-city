package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 预约服务统计图表 Response VO")
@Data
public class ReserveListChartRespVO {

    @Schema(description = "预约量趋势数据(折线图渲染),包含日期、预约数量")
    private List<Map<String, Object>> reserveTrendList;

    @Schema(description = "预约类型分布数据(柱状图渲染),包含预约类型、数量")
    private List<Map<String, Object>> reserveTypeCountList;

    @Schema(description = "总预约量")
    private Integer totalReserveCount;

    @Schema(description = "预约成功率,已生效 + 已完成 / 总数")
    private BigDecimal reserveSuccessRate;

}
