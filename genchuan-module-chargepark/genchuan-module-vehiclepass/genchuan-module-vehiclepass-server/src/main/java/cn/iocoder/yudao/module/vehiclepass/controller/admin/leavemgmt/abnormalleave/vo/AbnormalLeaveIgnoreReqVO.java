package cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 异常离场忽略 Request VO")
@Data
public class AbnormalLeaveIgnoreReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "记录主键ID不能为空")
    private Long id;

    @Schema(description = "忽略理由", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "忽略理由不能为空")
    private String ignoreReason;

}