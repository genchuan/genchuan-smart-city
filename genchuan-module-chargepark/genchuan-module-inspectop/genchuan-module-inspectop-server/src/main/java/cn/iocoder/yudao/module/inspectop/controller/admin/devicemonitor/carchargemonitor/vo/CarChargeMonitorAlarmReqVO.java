package cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.carchargemonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Schema(description = "巡查巡检 - 汽车充电监测告警 Request VO")
@Data
public class CarChargeMonitorAlarmReqVO {

    @Schema(description = "监测记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "监测记录ID不能为空")
    private Long id;

    @Schema(description = "告警备注", example = "充电设备离线，触发告警")
    private String alarmRemark;
}