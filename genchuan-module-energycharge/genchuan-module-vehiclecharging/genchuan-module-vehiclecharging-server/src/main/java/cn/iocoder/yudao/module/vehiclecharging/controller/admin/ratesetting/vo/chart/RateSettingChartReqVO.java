package cn.iocoder.yudao.module.vehiclecharging.controller.admin.ratesetting.vo.chart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "汽车充电 - 费率设置分布图表 Request VO")
public class RateSettingChartReqVO {

    @Schema(description = "时间范围：近7天、近30天、本月、自定义时间", example = "近30天")
    private String timeRange;

    @Schema(description = "开始时间，格式 yyyy-MM-dd", example = "2025-03-01")
    private String startTime;

    @Schema(description = "结束时间，格式 yyyy-MM-dd", example = "2025-03-31")
    private String endTime;
}
