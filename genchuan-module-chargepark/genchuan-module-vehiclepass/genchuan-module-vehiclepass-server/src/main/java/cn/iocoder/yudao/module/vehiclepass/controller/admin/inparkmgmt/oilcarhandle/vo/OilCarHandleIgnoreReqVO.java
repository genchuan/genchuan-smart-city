package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 油车占位处置忽略 Request VO")
@Data
public class OilCarHandleIgnoreReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "记录主键ID不能为空")
    private Long id;

    @Schema(description = "忽略理由", requiredMode = Schema.RequiredMode.REQUIRED, example = "临时停靠，已告知尽快离开")
    @NotBlank(message = "忽略理由不能为空")
    private String ignoreReason;

}