package cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 对账记录统计图表 Request VO")
@Data
public class ReconcileRecordChartReqVO {

    @Schema(description = "统计开始时间")
    private LocalDateTime startTime;

    @Schema(description = "统计结束时间")
    private LocalDateTime endTime;
}
