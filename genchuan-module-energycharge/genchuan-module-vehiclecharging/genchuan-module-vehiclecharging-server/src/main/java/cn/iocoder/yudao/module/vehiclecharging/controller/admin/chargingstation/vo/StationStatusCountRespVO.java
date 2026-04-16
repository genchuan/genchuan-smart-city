package cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "场站状态统计 VO")
public class StationStatusCountRespVO {

    @Schema(description = "场站状态：未启用/已启用/已停用", example = "已启用")
    private String stationStatus;

    @Schema(description = "数量", example = "105")
    private Integer count;
}