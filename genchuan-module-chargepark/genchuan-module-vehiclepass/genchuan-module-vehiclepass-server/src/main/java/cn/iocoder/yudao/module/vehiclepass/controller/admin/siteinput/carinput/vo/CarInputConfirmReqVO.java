package cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 车辆录入确认 Request VO")
@Data
public class CarInputConfirmReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "记录主键ID不能为空")
    private Long id;

}