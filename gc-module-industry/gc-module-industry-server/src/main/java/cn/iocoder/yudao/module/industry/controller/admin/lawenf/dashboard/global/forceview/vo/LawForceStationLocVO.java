package cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.forceview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "执法站点定位信息返回对象")
public class LawForceStationLocVO {

    @Schema(description = "站点X坐标")
    private Double stationCoordX;

    @Schema(description = "站点Y坐标")
    private Double stationCoordY;

    @Schema(description = "12位地区码，省市县街道社区")
    private String regionFullCode;
}
