package cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 稽查任务更新进度 Request VO")
@Data
public class InspectTaskUpdateProgressReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "记录主键ID不能为空")
    private Long id;

    @Schema(description = "任务进度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "任务进度不能为空")
    private String taskProgress;

}