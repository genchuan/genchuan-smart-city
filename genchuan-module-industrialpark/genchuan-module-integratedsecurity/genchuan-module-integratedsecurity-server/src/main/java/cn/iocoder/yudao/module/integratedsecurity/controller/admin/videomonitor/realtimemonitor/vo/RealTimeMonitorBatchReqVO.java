package cn.iocoder.yudao.module.integratedsecurity.controller.admin.videomonitor.realtimemonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Schema(description = "管理后台 - 实时监控批量操作 Request VO")
@Data
public class RealTimeMonitorBatchReqVO {

    @Schema(description = "监控记录ID列表", requiredMode = Schema.RequiredMode.REQUIRED, example = "[1,2,3]")
    @NotEmpty(message = "监控记录ID列表不能为空")
    private List<Long> ids;

}
