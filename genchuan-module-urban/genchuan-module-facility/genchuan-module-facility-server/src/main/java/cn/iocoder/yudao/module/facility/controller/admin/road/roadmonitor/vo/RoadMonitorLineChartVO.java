package cn.iocoder.yudao.module.facility.controller.admin.road.roadmonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "道路监测折线图数据")
@Data
public class RoadMonitorLineChartVO {

    @Schema(description = "时间轴（近24小时每小时）")
    private List<String> xAxis;

    @Schema(description = "坑洼数量")
    private List<BigDecimal> potholeNumList;

    @Schema(description = "裂缝长度")
    private List<BigDecimal> crackLengthList;

    @Schema(description = "路面温度")
    private List<BigDecimal> roadTempList;

    @Schema(description = "交通流量")
    private List<BigDecimal> trafficFlowList;

}
