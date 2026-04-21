package cn.iocoder.yudao.module.inspectop.controller.admin.inspecttask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Schema(description = "巡查巡检 - 巡检任务批量派发 Request VO")
@Data
public class InspectTaskBatchDispatchReqVO {

    @Schema(description = "任务ID数组", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "任务ID数组不能为空")
    private List<Long> ids;

    @Schema(description = "派发的巡检人员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "巡检人员ID不能为空")
    private Long userId;
}