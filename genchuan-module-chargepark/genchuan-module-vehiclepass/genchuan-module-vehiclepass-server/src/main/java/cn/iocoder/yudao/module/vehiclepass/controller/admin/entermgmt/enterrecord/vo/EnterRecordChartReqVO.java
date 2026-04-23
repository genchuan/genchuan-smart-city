package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.enterrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "入场记录 - 图表统计 Request VO")
@Data
public class EnterRecordChartReqVO {

    @Schema(description = "开始时间戳", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "开始时间不能为空")
    private String startTime;

    @Schema(description = "结束时间戳", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "结束时间不能为空")
    private String endTime;

    @Schema(description = "场站ID")
    private Long stationId;

}