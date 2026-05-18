package cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.vehicleaccess.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "管理后台 - 车牌识别 Request VO")
@Data
public class VehicleAccessRecognizeReqVO {

    @Schema(description = "车牌图片Base64", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "车牌图片不能为空")
    private String plateImg;

}
