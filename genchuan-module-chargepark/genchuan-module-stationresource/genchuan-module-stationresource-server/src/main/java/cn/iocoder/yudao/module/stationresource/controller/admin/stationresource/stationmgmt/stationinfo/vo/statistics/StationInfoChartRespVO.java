package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.statistics;

import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.statistics.CardDataRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.statistics.StationMapRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.statistics.TypeCountBarRespVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "场站信息统计（地图+柱状图+卡片）")
public class StationInfoChartRespVO {
//
    @Schema(description = "地图点位列表")
    private List<StationMapRespVO> stationMapList;

    @Schema(description = "类型统计柱状图")
    private List<TypeCountBarRespVO> typeCountBarList;

    @Schema(description = "卡片统计")
    private CardDataRespVO cardData;
}