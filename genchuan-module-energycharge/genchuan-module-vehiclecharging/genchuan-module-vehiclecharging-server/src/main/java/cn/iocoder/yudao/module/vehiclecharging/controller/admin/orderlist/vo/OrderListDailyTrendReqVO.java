package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderlist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Schema(description = "汽车充电 - 每日订单趋势钻取 Request VO")
@Data
public class OrderListDailyTrendReqVO {

    @Schema(description = "开始时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2026-03-01")
    @NotNull(message = "开始时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startTime;

    @Schema(description = "结束时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2026-04-31")
    @NotNull(message = "结束时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endTime;

    @Schema(description = "场站ID（可选）", example = "1")
    private Long stationId;
}