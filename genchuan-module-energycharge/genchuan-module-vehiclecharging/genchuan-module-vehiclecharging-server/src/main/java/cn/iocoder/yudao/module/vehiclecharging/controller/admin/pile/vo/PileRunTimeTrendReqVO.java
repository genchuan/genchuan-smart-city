package cn.iocoder.yudao.module.vehiclecharging.controller.admin.pile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 充电桩运行时长趋势 Request VO")
@Data
public class PileRunTimeTrendReqVO {

    @Schema(description = "所属场站ID，关联充电场站表charging_station", example = "27260")
    private Long stationId;

    @Schema(description = "开始时间（时间戳）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1743542400000")
    private String startTime;

    @Schema(description = "结束时间（时间戳）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1746144000000")
    private String endTime;

}
