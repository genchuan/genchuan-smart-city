package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 积分活动图表统计 Request VO")
@Data
public class PointActivityChartReqVO {

    @Schema(description = "统计开始时间", example = "1704067200000")
    private Long startTime;

    @Schema(description = "统计结束时间", example = "1706745600000")
    private Long endTime;

    @Schema(description = "场站ID", example = "1")
    private Long stationId;

}