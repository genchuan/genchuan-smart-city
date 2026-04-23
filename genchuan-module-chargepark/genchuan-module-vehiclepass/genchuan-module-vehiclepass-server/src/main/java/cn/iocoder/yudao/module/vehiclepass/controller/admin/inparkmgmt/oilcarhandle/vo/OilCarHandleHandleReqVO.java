package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 油车占位处置处置 Request VO")
@Data
public class OilCarHandleHandleReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "记录主键ID不能为空")
    private Long id;

    @Schema(description = "处置方式", requiredMode = Schema.RequiredMode.REQUIRED, example = "电话通知车主挪车")
    @NotBlank(message = "处置方式不能为空")
    private String handleMethod;

}