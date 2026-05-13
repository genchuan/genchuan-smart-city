package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.identify.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "管理后台 - 车牌识别确认 Request VO")
public class PlateIdentifyConfirmReqVO {

    @NotNull(message = "记录主键ID不能为空")
    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;
}