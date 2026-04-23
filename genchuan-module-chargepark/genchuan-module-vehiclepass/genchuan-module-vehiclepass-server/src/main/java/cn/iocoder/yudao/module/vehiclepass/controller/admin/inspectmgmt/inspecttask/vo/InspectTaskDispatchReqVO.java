package cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 稽查任务派发 Request VO")
@Data
public class InspectTaskDispatchReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "记录主键ID不能为空")
    private Long id;

    @Schema(description = "执行人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "执行人ID不能为空")
    private Long executeUserId;

}