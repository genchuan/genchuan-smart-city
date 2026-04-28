package cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.resulthandle.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 结果处置执行 Request VO")
@Data
public class ResultHandleExecuteReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "记录主键ID不能为空")
    private Long id;

    @Schema(description = "整改状态：未整改/已整改", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "整改状态不能为空")
    private String rectifyStatus;

}