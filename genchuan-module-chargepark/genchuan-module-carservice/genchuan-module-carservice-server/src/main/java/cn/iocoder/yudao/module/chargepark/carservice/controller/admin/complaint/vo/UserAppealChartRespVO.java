package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 用户申诉统计图表 Response VO")
@Data
public class UserAppealChartRespVO {

    @Schema(description = "申诉量趋势数据(折线图渲染)")
    private List<Map<String, Object>> appealTrendList;

    @Schema(description = "待申诉数,待审核 + 待处置的总数")
    private Integer waitAppealCount;

    @Schema(description = "处理完成率,已完成 / 总数")
    private BigDecimal handleFinishRate;

}
