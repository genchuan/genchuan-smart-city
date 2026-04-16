package cn.iocoder.yudao.module.vehiclecharging.controller.admin.modulealarm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Schema(description = "汽车充电 - 模块告警记录排查 Request VO")
@Data
public class ModuleAlarmCheckReqVO {

    @Schema(description = "告警记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "告警记录ID不能为空")
    private Long id;

    @Schema(description = "排查原因", requiredMode = Schema.RequiredMode.REQUIRED, example = "订单服务接口超时，经排查是数据库连接池耗尽导致")
    @NotBlank(message = "排查原因不能为空")
    private String checkReason;

}
