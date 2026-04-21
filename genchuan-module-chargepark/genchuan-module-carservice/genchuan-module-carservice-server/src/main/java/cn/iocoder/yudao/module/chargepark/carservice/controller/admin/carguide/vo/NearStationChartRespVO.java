package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 周边场站统计图表 Response VO")
@Data
public class NearStationChartRespVO {

    /** 占位字段:数据源为 vehiclecharging/park 模块的场站表(未交付),待外部模块上线后填充 */
    @Schema(description = "周边场站分布数据(地图渲染)。占位字段,待场站模块上线后填充")
    private List<Map<String, Object>> stationLocationList;

    /** 占位字段:依赖场站经纬度计算距离区间分布,待外部模块上线后填充 */
    @Schema(description = "场站距离分布数据(柱状图渲染)。占位字段,待场站模块上线后填充")
    private List<Map<String, Object>> distanceCountList;

    @Schema(description = "周边场站数(平均查询返回数量)")
    private Integer totalStationCount;

    @Schema(description = "空位场站数(平均查询返回数量)")
    private Integer emptyStationCount;

}
