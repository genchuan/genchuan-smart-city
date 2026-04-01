package cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.chart;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class StatusMonitorChartRespVO {
    // 卡片数据
    private Integer totalCount;
    private Integer normalCount;
    private Integer abnormalCount;
    private Integer handlingCount;
    private Integer recoveredCount;

    // 折线图数据
    private List<ParamTrend> paramTrendList;

    // 地图标注数据
    private List<AbnormalPoint> abnormalPointList;

}
