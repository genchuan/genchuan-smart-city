package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 服务运营分析图表 Response VO")
@Data
public class ServiceOpReportChartRespVO {

    @Schema(description = "服务运营趋势数据(折线图渲染)")
    private List<Map<String, Object>> operateTrendList;

    @Schema(description = "服务类型分布数据(柱状图渲染)")
    private List<Map<String, Object>> serviceTypeCountList;

    @Schema(description = "服务核心指标(救援完成率/预约成功率/投诉处理率等)")
    private Map<String, Object> coreIndex;

}
