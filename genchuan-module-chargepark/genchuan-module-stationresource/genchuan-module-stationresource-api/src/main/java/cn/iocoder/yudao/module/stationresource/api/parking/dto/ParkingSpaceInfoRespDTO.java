package cn.iocoder.yudao.module.stationresource.api.parking.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "RPC - 车位信息响应")
@Data
public class ParkingSpaceInfoRespDTO {

    @Schema(description = "车位 ID")
    private Long id;

    @Schema(description = "车位编号(如 PS001)")
    private String spaceNo;

    @Schema(description = "场站 ID")
    private Long stationId;

}
