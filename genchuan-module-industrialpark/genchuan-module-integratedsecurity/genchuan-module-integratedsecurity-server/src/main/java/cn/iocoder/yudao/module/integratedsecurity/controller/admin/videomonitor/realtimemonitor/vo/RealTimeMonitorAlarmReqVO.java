package cn.iocoder.yudao.module.integratedsecurity.controller.admin.videomonitor.realtimemonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema(description = "管理后台 - 实时监控告警 Request VO")
@Data
public class RealTimeMonitorAlarmReqVO {

    @Schema(description = "监控记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "监控记录ID不能为空")
    private Long id;

    @Schema(description = "告警内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "发现可疑人员闯入")
    @NotEmpty(message = "告警内容不能为空")
    private String alarmContent;

}
