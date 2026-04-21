package cn.iocoder.yudao.module.inspectop.controller.admin.inspecttask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "巡查巡检 - 巡检任务认领 Request VO")
@Data
public class InspectTaskClaimReqVO {

    @Schema(description = "任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "任务ID不能为空")
    private Long id;
}