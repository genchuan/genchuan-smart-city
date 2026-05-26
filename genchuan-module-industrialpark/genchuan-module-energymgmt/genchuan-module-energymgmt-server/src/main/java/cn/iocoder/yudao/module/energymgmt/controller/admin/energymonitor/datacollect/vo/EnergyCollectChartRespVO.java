package cn.iocoder.yudao.module.energymgmt.controller.admin.energymonitor.datacollect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 能耗采集表图表数据 Response VO")
@Data
public class EnergyCollectChartRespVO {

    @Schema(description = "采集设备数")
    private Long deviceCount;

    @Schema(description = "正常采集数")
    private Long normalCount;

    @Schema(description = "异常采集数")
    private Long exceptionCount;

    @Schema(description = "总能耗值")
    private BigDecimal totalEnergy;

    @Schema(description = "实时能耗采集趋势数据")
    private List<EnergyCollectChartRespVO.RealTimeTrendVO> realTimeTrend;

    @Schema(description = "分时段能耗趋势数据")
    private List<EnergyCollectChartRespVO.PeriodTrendVO> periodTrend;

    @Schema(description = "实时能耗采集趋势数据")
    @Data
    public static class RealTimeTrendVO {
        private String time;
        private BigDecimal value;
    }

    @Schema(description = "分时段能耗趋势数据")
    @Data
    public static class PeriodTrendVO {
        private String time;
        private BigDecimal value;
    }

}
