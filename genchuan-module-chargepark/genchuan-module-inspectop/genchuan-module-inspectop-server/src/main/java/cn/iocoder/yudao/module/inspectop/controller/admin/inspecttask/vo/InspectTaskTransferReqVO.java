package cn.iocoder.yudao.module.inspectop.controller.admin.inspecttask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "巡查巡检 - 巡检任务转派 Request VO")
@Data
public class InspectTaskTransferReqVO {

    @Schema(description = "任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "任务ID不能为空")
    private Long id;

    @Schema(description = "目标巡检人员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "目标巡检人员ID不能为空")
    private Long targetUserId;
}