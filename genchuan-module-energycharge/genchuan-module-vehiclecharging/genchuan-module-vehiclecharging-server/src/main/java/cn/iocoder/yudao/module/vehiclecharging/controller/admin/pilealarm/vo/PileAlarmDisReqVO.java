package cn.iocoder.yudao.module.vehiclecharging.controller.admin.pilealarm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "充电桩告警 - 派单 Request VO")
public class PileAlarmDisReqVO {

    @Schema(description = "告警ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "告警ID不能为空")
    private Long id;

    @Schema(description = "处理人员ID（system_users.id）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "处理人员ID不能为空")
    private String handleUserId;

}