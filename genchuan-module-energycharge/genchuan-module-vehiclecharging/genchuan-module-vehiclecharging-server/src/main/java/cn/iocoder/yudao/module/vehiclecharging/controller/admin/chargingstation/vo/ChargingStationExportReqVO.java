package cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "充电站导出 Request VO")
@Data
public class ChargingStationExportReqVO extends PageParam {

    @Schema(description = "场站编号", example = "CZ001")
    private String stationCode;

    @Schema(description = "场站名称", example = "泉州充电站")
    private String stationName;

    @Schema(description = "场站地址", example = "福建省泉州市")
    private String address;

    @Schema(description = "合作模式", example = "自营")
    private String coopMode;

    @Schema(description = "场站状态", example = "已启用")
    private String stationStatus;
}