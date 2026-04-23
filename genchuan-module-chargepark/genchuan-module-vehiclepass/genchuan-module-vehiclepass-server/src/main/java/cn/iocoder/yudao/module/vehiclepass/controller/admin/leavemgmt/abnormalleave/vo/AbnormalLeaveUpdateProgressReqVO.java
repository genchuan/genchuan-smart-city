package cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 异常离场更新进度 Request VO")
@Data
public class AbnormalLeaveUpdateProgressReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "记录主键ID不能为空")
    private Long id;

    @Schema(description = "处置进度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "处置进度不能为空")
    private String handleProgress;

}