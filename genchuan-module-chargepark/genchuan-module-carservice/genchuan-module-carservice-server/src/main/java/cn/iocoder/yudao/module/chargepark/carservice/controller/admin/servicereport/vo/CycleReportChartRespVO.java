package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 周期报表 图表 Response VO")
@Data
public class CycleReportChartRespVO {

    @Schema(description = "卡片数据:rescueCompleteRate/reserveSuccessRate/complaintHandleRate/findCarSuccessRate/spacePushSuccessRate/effectiveWordingCount")
    private Map<String, Object> cardData;

    @Schema(description = "折线图数据(救援量趋势/预约量趋势/投诉量趋势/空位推送量趋势),每项 {name, data:[{date, count}]}")
    private List<Map<String, Object>> lineData;

    @Schema(description = "柱状图数据(救援类型/预约类型/纠纷类型分布),每项 {name, data:[{type, count}]}")
    private List<Map<String, Object>> barData;

    @Schema(description = "地图数据(救援位置/场站车位分布),每项 {name, data:[{lon, lat, ...}]}")
    private List<Map<String, Object>> mapData;

    @Schema(description = "饼图数据(话术类型占比/服务状态占比),每项 {name, data:[{type, count}]}")
    private List<Map<String, Object>> pieData;

}
