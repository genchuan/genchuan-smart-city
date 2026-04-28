package cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "管理后台 - 车辆录入统计 Request VO")
@Data
public class CarInputChartReqVO {

    @Schema(description = "统计开始时间，时间戳格式", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "1774147200")
    private String startTime;

    @Schema(description = "统计结束时间，时间戳格式", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "1775011200")
    private String endTime;

    @Schema(description = "片区ID")
    private Long areaId;

}