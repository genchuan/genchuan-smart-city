package cn.iocoder.yudao.module.vehiclecharging.controller.admin.abnormalorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "异常订单统计图表 Request VO")
@Data
public class AbnormalOrderChartReqVO {

    @Schema(description = "时间范围：近7天、近30天、本月、自定义", example = "近30天")
    private String timeRange;

    @Schema(description = "开始时间", example = "2025-03-01")
    private String startTime;

    @Schema(description = "结束时间", example = "2025-03-31")
    private String endTime;
}