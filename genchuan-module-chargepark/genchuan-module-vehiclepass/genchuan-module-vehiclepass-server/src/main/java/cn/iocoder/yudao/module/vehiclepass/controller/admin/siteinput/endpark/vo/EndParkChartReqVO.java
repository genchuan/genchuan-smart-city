package cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Schema(description = "管理后台 - 结束停车统计 Request VO")
@Data
public class EndParkChartReqVO {

    @Schema(description = "统计开始时间，时间戳格式", requiredMode = Schema.RequiredMode.REQUIRED, example = "1774147200")
    @NotNull(message = "统计开始时间不能为空")
    private Long startTime;

    @Schema(description = "统计结束时间，时间戳格式", requiredMode = Schema.RequiredMode.REQUIRED, example = "1775011200")
    @NotNull(message = "统计结束时间不能为空")
    private Long endTime;

    @Schema(description = "片区ID", example = "1")
    private Long areaId;

}