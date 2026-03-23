package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

// 响应VO - 趋势图表子对象
@Data
public class TrendChartRespVO {
    private List<String> xAxis; // X轴：小时维度 ["00时","01时"...]
    private List<BigDecimal> yAxis; // Y轴：指标数值
    private String unit; // 单位：°/cm
    private BigDecimal thresholdValue; // 预警阈值
    private String type = "line"; // 固定为line
}
