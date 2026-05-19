package cn.iocoder.yudao.module.integratedsecurity.controller.admin.videomonitor.realtimemonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Schema(description = "管理后台 - 实时监控录像 Request VO")
@Data
public class RealTimeMonitorRecordReqVO {

    @Schema(description = "监控记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "监控记录ID不能为空")
    private Long id;

    @Schema(description = "录像时长(秒)，默认300", example = "600")
    private Integer recordDuration;

}
