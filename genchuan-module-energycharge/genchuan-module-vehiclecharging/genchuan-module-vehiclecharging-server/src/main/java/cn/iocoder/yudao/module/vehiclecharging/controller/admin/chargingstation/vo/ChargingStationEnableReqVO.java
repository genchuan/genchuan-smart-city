package cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.List;

@Schema(description = "充电站 - 批量启用 Request VO")
@Data
public class ChargingStationEnableReqVO {

    @Schema(description = "场站ID 集合", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "场站ID 不能为空")
    private List<Long> ids;
}