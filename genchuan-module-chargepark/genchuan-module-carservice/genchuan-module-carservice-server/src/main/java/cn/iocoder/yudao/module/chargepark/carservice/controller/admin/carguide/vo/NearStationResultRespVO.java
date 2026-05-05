package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 周边场站查询结果(快照) Response VO")
@Data
public class NearStationResultRespVO {

    @Schema(description = "结果ID")
    private Long id;

    @Schema(description = "关联 near_station.id")
    private Long nearStationId;

    @Schema(description = "场站ID")
    private Long stationId;

    @Schema(description = "场站名称(快照,与查询时一致)")
    private String stationName;

    @Schema(description = "场站经度")
    private BigDecimal lon;

    @Schema(description = "场站纬度")
    private BigDecimal lat;

    @Schema(description = "到查询点的直线距离(km)")
    private BigDecimal distanceKm;

    @Schema(description = "当时是否有空位")
    private Boolean hasEmpty;

    @Schema(description = "当时空位数")
    private Integer emptyCount;

    @Schema(description = "当时车位总数")
    private Integer totalCount;
}
