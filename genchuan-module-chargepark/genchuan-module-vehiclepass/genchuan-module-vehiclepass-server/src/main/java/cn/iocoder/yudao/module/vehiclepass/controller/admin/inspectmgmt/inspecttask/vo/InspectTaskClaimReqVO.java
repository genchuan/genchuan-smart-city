package cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 稽查任务认领 Request VO")
@Data
public class InspectTaskClaimReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "记录主键ID不能为空")
    private Long id;

}