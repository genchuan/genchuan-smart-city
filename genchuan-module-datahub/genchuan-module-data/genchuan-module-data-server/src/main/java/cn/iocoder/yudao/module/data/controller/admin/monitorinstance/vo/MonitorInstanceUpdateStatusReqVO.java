package cn.iocoder.yudao.module.data.controller.admin.monitorinstance.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 批量更新监测部件实例状态 Request VO")
@Data
public class MonitorInstanceUpdateStatusReqVO {

    @Schema(description = "监测部件实例ID列表", requiredMode = Schema.RequiredMode.REQUIRED, example = "[1, 2, 3]")
    @NotEmpty(message = "监测部件实例ID列表不能为空")
    private List<Long> ids;

    @Schema(description = "目标运行状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "normal")
    @NotNull(message = "目标运行状态不能为空")
    private String runStatus;
}