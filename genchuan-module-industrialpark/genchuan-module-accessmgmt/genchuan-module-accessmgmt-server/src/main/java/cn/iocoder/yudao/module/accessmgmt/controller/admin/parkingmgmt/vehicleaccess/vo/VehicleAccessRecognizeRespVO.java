package cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.vehicleaccess.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 车牌识别 Response VO")
@Data
public class VehicleAccessRecognizeRespVO {

    @Schema(description = "车牌号")
    private String plateNo;

    @Schema(description = "车辆类型（小型车/大型车）")
    private String vehicleType;

    @Schema(description = "是否识别成功")
    private Boolean success;

}
