package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderlist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Schema(description = "汽车充电 - 订单交易趋势图 Request VO")
@Data
public class OrderListChartReqVO {

    @Schema(description = "时间范围（近7天、近30天、本月、自定义）", example = "近30天")
    private String timeRange;

    @Schema(description = "开始时间", example = "2025-03-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startTime;

    @Schema(description = "结束时间", example = "2025-03-31")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endTime;
}