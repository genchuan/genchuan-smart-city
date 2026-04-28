package cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.paycheck.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "管理后台 - 缴费核验统计 Request VO")
@Data
public class PayCheckChartReqVO {

    @Schema(description = "统计开始时间，时间戳格式", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "1774147200")
    private String startTime;

    @Schema(description = "统计结束时间，时间戳格式", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "1775011200")
    private String endTime;

    @Schema(description = "场站ID", example = "1")
    private Long stationId;

}