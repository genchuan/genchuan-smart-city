package cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.chart;

import lombok.Data;

import java.math.BigDecimal;

@Data
public  class ParamTrend {
    private String time;         // 时间格式: "HH:mm"
    private BigDecimal voltage;  // 平均电压(V)
    private BigDecimal current;  // 平均电流(A)
    private BigDecimal power;    // 平均功率(kW)
}
