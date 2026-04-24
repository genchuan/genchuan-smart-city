package cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.resulthandle.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 结果处置驳回 Request VO")
@Data
public class ResultHandleRejectReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "记录主键ID不能为空")
    private Long id;

    @Schema(description = "驳回理由", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "驳回理由不能为空")
    private String rejectReason;

}