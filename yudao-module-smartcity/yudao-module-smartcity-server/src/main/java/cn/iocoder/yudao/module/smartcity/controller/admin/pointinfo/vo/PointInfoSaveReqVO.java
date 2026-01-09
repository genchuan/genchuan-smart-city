package cn.iocoder.yudao.module.smartcity.controller.admin.pointinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 监测点位新增/修改 Request VO")
@Data
public class PointInfoSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "11457")
    private Long id;

    @Schema(description = "点位编号")
    private String pointNumber;

    @Schema(description = "点位名称", example = "王五")
    private String pointName;

    @Schema(description = "点位类型", example = "1")
    private String pointType;

    @Schema(description = "所属区域")
    private String belongingArea;

    @Schema(description = "经纬度坐标")
    private String latitudeLongitudeCoordinate;

    @Schema(description = "海拔高度")
    private String altitude;

    @Schema(description = "设备信息")
    private String deviceInfo;

}