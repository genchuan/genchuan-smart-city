package cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.forceview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "执法车辆定位信息返回对象")
public class LawForceVehicleLocVO {

    @Schema(description = "执法车辆ID")
    private String lawEnfVehicleId;

    @Schema(description = "车牌号")
    private String licensePlate;

    @Schema(description = "车辆经度坐标")
    private Double vehicleCoordX;

    @Schema(description = "车辆纬度坐标")
    private Double vehicleCoordY;

    @Schema(description = "12位地区码，省市县街道社区")
    private String regionFullCode;
}
