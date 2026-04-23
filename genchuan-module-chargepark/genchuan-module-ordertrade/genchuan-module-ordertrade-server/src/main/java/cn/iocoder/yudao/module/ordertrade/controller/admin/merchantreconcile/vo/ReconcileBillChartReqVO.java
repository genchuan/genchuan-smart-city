package cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 商户对账单统计图表 Request VO")
@Data
public class ReconcileBillChartReqVO {

    @Schema(description = "统计开始时间")
    private LocalDateTime startTime;

    @Schema(description = "统计结束时间")
    private LocalDateTime endTime;
}
