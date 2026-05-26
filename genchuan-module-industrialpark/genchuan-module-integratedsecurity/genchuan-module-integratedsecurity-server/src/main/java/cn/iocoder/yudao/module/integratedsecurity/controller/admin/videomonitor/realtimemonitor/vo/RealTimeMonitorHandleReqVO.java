package cn.iocoder.yudao.module.integratedsecurity.controller.admin.videomonitor.realtimemonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema(description = "管理后台 - 实时监控处置 Request VO")
@Data
public class RealTimeMonitorHandleReqVO {

    @Schema(description = "监控记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "监控记录ID不能为空")
    private Long id;

    @Schema(description = "处置结果", requiredMode = Schema.RequiredMode.REQUIRED, example = "已核实为工作人员，误告警，已解除")
    @NotEmpty(message = "处置结果不能为空")
    private String handleResult;

}
