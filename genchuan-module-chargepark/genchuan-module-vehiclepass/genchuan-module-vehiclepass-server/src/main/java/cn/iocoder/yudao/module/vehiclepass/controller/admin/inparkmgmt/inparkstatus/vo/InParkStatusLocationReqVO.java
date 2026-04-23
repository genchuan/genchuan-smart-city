package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 在停状态定位 Request VO")
@Data
public class InParkStatusLocationReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "记录主键ID不能为空")
    private Long id;

}