package cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "管理后台 - 异常离场统计 Request VO")
@Data
public class AbnormalLeaveChartReqVO {

    @Schema(description = "统计开始时间，时间戳格式", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "1674147200")
    private String startTime;

    @Schema(description = "统计结束时间，时间戳格式", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "1974147200")
    private String endTime;

    @Schema(description = "场站ID")
    private Long stationId;

}