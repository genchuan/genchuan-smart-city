package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.statistics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "片区场站数量柱状图")
public class StationCountBarItemVO {
    @Schema(description = "片区id", example = "1")
    private Long areaId;
    @Schema(description = "片区名称", example = "丰泽片区")
    private String name;

    @Schema(description = "数量", example = "12")
    private Integer value;
}
