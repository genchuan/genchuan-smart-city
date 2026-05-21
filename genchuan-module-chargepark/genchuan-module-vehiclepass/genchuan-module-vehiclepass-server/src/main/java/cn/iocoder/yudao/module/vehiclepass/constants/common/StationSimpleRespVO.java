package cn.iocoder.yudao.module.vehiclepass.constants.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 场站精简信息 Response VO")
@Data
public class StationSimpleRespVO {

    @Schema(description = "场站ID", example = "1")
    private Long stationId;

    @Schema(description = "场站名称", example = "A停车场")
    private String stationName;
}
