package cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 各区域充电场站数量统计 Response VO")
@Data
public class ChargingStationAreaCountRespVO {

    @Schema(description = "区域ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "350503")
    private Long areaId;

    @Schema(description = "区域名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "丰泽区")
    private String areaName;

    @Schema(description = "该区域场站数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "35")
    private Integer stationCount;

    @Schema(description = "该区域已启用场站数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "32")
    private Integer enableCount;

}