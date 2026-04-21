package cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.spacemonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Schema(description = "巡查巡检 - 车位状态监测告警 Request VO")
@Data
public class SpaceMonitorAlarmReqVO {

    @Schema(description = "监测记录ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "监测记录ID不能为空")
    private Long id;

    @Schema(description = "告警备注")
    private String alarmRemark;
}