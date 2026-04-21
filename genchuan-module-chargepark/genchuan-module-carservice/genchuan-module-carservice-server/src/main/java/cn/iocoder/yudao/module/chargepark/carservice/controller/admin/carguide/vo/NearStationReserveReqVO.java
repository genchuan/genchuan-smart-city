package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 周边场站预订跳转 Request VO")
@Data
public class NearStationReserveReqVO {

    @Schema(description = "周边场站查询记录 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "查询记录 ID 不能为空")
    private Long id;

    @Schema(description = "目标场站 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "101")
    @NotNull(message = "目标场站 ID 不能为空")
    private Long stationId;

}
