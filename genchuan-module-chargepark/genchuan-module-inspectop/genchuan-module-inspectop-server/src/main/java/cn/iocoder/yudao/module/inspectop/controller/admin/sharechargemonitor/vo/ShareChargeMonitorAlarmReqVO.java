package cn.iocoder.yudao.module.inspectop.controller.admin.sharechargemonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Schema(description = "巡查巡检 - 共享充电监测告警更新 Request VO")
@Data
public class ShareChargeMonitorAlarmReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "监测记录ID不能为空")
    private Long id;

    @Schema(description = "告警备注")
    private String alarmRemark;

}