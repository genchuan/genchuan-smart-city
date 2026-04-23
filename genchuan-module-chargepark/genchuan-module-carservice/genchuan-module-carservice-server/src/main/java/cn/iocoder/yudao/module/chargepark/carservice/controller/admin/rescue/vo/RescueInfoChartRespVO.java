package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 救援服务统计图表 Response VO")
@Data
public class RescueInfoChartRespVO {

    /**
     * 救援位置分布数据(地图渲染)。
     *
     * 当前每条记录返回 {id, location(文本地址), status},因为 rescue_info 表只存
     * varchar 地址,不存经纬度。前端如需地图渲染,需自行 geocode 或等待后续在 DDL
     * 中追加 lon/lat 字段。
     */
    @Schema(description = "救援位置分布数据(地图渲染)。当前结构 {id,location,status},无 lon/lat,前端需自行 geocode")
    private List<Map<String, Object>> rescueLocationList;

    @Schema(description = "救援时效趋势数据(折线图渲染),包含日期、平均处理时长")
    private List<Map<String, Object>> trendList;

    @Schema(description = "待救援数,待派发 + 待认领状态的总数")
    private Integer waitRescueCount;

    @Schema(description = "待派发数(status=待派发)")
    private Integer waitDispatchCount;

    @Schema(description = "待认领数(status=待认领)")
    private Integer waitClaimCount;

    @Schema(description = "救援完成率,已完成 / 总数")
    private BigDecimal finishRate;

}
