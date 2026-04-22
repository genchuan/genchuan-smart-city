package cn.iocoder.yudao.module.inspectop.api.space.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 车位监测 RPC 响应 DTO。用于提供车位坐标/监测状态。
 */
@Schema(description = "RPC - 车位监测响应")
@Data
public class SpaceMonitorRespDTO {

    @Schema(description = "监测记录 ID")
    private Long id;

    @Schema(description = "车位 ID")
    private Long spaceId;

    @Schema(description = "场站 ID")
    private Long stationId;

    @Schema(description = "经度")
    private BigDecimal longitude;

    @Schema(description = "纬度")
    private BigDecimal latitude;

    @Schema(description = "监测状态:1=正常 2=异常(字典 space_monitor_status)")
    private String monitorStatus;

}
