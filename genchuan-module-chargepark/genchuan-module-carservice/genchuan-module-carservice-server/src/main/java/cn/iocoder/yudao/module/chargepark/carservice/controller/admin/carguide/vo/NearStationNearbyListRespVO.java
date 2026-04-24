package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 周边场站·附近场站查询 Response VO")
@Data
public class NearStationNearbyListRespVO {

    @Schema(description = "本次查询使用的半径（km）", example = "5")
    private BigDecimal radiusKm;

    @Schema(description = "半径内的场站列表（按距离升序）")
    private List<NearStationNearbyRespVO> list;

}
