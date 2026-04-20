package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 纠纷调解统计图表 Response VO")
@Data
public class DisputeMediateChartRespVO {

    @Schema(description = "纠纷量趋势数据(折线图渲染)")
    private List<Map<String, Object>> disputeTrendList;

    @Schema(description = "待调解数,待调解状态的纠纷总数")
    private Integer waitMediateCount;

    @Schema(description = "调解完成率,已完成 / 总数")
    private BigDecimal mediateFinishRate;

}
