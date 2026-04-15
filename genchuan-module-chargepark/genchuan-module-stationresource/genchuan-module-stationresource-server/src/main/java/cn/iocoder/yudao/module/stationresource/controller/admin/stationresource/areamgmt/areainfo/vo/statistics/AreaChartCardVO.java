package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.statistics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "片区统计卡片数据")
public class AreaChartCardVO {
    @Schema(description = "总片区数", example = "35")
    private Integer totalAreaCount;

    @Schema(description = "总场站数", example = "120")
    private Integer totalStationCount;
}
