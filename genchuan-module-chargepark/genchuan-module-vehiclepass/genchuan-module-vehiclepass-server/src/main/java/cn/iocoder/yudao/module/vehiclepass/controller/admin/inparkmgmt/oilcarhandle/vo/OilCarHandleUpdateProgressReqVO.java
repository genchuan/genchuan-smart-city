package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 油车占位处置更新进度 Request VO")
@Data
public class OilCarHandleUpdateProgressReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "记录主键ID不能为空")
    private Long id;

    @Schema(description = "处置进度", requiredMode = Schema.RequiredMode.REQUIRED, example = "已通知车主，车主已挪车")
    @NotBlank(message = "处置进度不能为空")
    private String handleProgress;

}