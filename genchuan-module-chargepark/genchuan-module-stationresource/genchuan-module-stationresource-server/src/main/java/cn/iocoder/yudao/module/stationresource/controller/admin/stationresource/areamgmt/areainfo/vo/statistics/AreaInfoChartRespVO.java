package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.statistics;

import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.statistics.AreaChartCardVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.statistics.AreaMapItemVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.statistics.StationCountBarItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "片区信息 - 数据可视化图表返回")
public class AreaInfoChartRespVO {
    @Schema(description = "地图点位列表")
    private List<AreaMapItemVO> areaMapList;

    @Schema(description = "柱状图数据")
    private List<StationCountBarItemVO> stationCountBarList;

    @Schema(description = "卡片统计数据")
    private AreaChartCardVO cardData;
}
