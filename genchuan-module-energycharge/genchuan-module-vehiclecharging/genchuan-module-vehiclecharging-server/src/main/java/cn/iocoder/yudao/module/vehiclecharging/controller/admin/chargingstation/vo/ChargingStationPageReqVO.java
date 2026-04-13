package cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import groovy.transform.EqualsAndHashCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "汽车充电 - 充电站分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class ChargingStationPageReqVO extends PageParam {

    @Schema(description = "场站编号", example = "CS202503")
    private String stationCode;

    @Schema(description = "区域编号", example = "1")
    private Long areaId;

    @Schema(description = "场站名称", example = "泉州充电站")
    private String stationName;

    @Schema(description = "场站地址", example = "福建省泉州市")
    private String address;

    @Schema(description = "合作模式", example = "self")
    private String coopMode;

    @Schema(description = "场站状态", example = "enabled")
    private String stationStatus;

}