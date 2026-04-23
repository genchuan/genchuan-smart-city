package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.statistics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "场站地图点位")
public class StationMapRespVO {
//
    @Schema(description = "场站ID", example = "1")
    private Long id;

    @Schema(description = "场站名称", example = "泉州万达充电站")
    private String name;

    @Schema(description = "经度", example = "118.675324")
    private Double lon;

    @Schema(description = "纬度", example = "24.896541")
    private Double lat;

    @Schema(description = "状态：已生效/未生效/已禁用", example = "已生效")
    private String status;
}