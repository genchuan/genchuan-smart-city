package cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import java.util.*;

@Schema(description = "管理后台 - 稽查任务批量派发 Request VO")
@Data
public class InspectTaskBatchDispatchReqVO {

    @Schema(description = "记录主键ID数组", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "记录主键ID数组不能为空")
    @Size(max = 200, message = "批量操作数量不能超过200")
    private List<Long> ids;

    @Schema(description = "执行人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "执行人ID不能为空")
    private Long executeUserId;

}