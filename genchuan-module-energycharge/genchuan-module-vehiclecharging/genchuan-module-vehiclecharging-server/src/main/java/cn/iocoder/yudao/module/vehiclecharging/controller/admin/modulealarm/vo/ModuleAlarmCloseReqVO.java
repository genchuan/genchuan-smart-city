package cn.iocoder.yudao.module.vehiclecharging.controller.admin.modulealarm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Schema(description = "汽车充电 - 模块告警记录销账 Request VO")
@Data
public class ModuleAlarmCloseReqVO {

    @Schema(description = "告警记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "告警记录ID不能为空")
    private Long id;

}
