package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingratio.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 分账比例统计图表 Request VO")
@Data
public class SharingRatioChartReqVO {

    @Schema(description = "统计开始时间（时间戳秒）", example = "1704067200")
    private Long timeRangeStart;

    @Schema(description = "统计结束时间（时间戳秒）", example = "1735689600")
    private Long timeRangeEnd;
}
