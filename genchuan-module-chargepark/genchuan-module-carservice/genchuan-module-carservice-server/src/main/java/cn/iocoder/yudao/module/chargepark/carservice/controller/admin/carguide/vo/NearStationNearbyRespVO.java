package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 周边场站·附近场站明细 Response VO")
@Data
public class NearStationNearbyRespVO {

    @Schema(description = "场站 ID（导航/预订接口的 stationId 参数就是它）", example = "101")
    private Long stationId;

    @Schema(description = "场站名称", example = "晋江中央墅府充电站")
    private String stationName;

    @Schema(description = "场站经度", example = "118.675324")
    private BigDecimal lon;

    @Schema(description = "场站纬度", example = "24.896541")
    private BigDecimal lat;

    @Schema(description = "距离查询点（km，保留 2 位小数）", example = "0.87")
    private BigDecimal distanceKm;

    @Schema(description = "空位数（来自 stationresource）", example = "5")
    private Integer emptySpace;

}
