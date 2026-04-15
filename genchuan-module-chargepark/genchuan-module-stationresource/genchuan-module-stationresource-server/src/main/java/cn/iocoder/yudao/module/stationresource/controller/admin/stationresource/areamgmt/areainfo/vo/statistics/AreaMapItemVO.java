package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.statistics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "片区地图点位")
public class AreaMapItemVO {
    @Schema(description = "片区ID", example = "1")
    private Long id;

    @Schema(description = "片区名称", example = "丰泽片区")
    private String name;

    @Schema(description = "经度", example = "118.675324")
    private Double lon;

    @Schema(description = "纬度", example = "24.896541")
    private Double lat;

    @Schema(description = "场站数量", example = "12")
    private Integer stationCount;
}
