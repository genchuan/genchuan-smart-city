package cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 开闸管理驳回 Request VO")
@Data
public class GateOpenRejectReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "记录主键ID不能为空")
    private Long id;

    @Schema(description = "驳回理由", requiredMode = Schema.RequiredMode.REQUIRED, example = "非紧急情况，禁止特殊开闸")
    @NotEmpty(message = "驳回理由不能为空")
    private String rejectReason;

}