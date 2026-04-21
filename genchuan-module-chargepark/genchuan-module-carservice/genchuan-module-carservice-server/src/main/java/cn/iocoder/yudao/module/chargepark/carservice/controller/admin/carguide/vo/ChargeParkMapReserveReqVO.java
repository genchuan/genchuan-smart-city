package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 充停地图预订跳转 Request VO")
@Data
public class ChargeParkMapReserveReqVO {

    @Schema(description = "充停地图查询记录 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "查询记录 ID 不能为空")
    private Long id;

    @Schema(description = "目标场站 ID（stationId 与 spaceId 至少传一个）", example = "101")
    private Long stationId;

    @Schema(description = "目标车位 ID（stationId 与 spaceId 至少传一个）", example = "201")
    private Long spaceId;

    @AssertTrue(message = "目标场站 ID 或目标车位 ID 至少传一个")
    public boolean isTargetPresent() {
        return stationId != null || spaceId != null;
    }

}
