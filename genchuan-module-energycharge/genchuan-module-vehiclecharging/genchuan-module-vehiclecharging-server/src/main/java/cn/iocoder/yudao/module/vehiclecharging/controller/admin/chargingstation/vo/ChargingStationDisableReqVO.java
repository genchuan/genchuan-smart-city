package cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;


@Schema(description = "充电站 - 停用 Request VO")
@Data
public class ChargingStationDisableReqVO {

    @Schema(description = "场站ID 集合", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "场站ID 不能为空")
    private List<Long> ids;

    @Schema(description = "停用原因", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "停用原因不能为空")
    private String stopReason;
}