package cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 稽查任务转派 Request VO")
@Data
public class InspectTaskTransferReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "记录主键ID不能为空")
    private Long id;

    @Schema(description = "目标执行人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "目标执行人ID不能为空")
    private Long targetUserId;

    @Schema(description = "转派理由", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "转派理由不能为空")
    private String transferReason;

}