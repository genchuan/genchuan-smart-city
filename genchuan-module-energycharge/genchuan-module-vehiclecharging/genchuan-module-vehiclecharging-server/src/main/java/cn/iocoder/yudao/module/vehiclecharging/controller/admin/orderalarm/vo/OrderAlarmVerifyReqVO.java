package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderalarm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 订单告警核实 Request VO")
@Data
public class OrderAlarmVerifyReqVO {

    @Schema(description = "告警 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "告警 ID 不能为空")
    private Long id;

    @Schema(description = "核实结果（正常 / 异常 / 误报）", requiredMode = Schema.RequiredMode.REQUIRED, example = "异常")
    @NotNull(message = "核实结果不能为空")
    private String verifyResult;

}