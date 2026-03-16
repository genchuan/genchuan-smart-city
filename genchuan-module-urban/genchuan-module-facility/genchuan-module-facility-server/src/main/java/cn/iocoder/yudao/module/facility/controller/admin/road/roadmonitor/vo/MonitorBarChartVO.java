package cn.iocoder.yudao.module.facility.controller.admin.road.roadmonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "监测柱状图统计")
public class MonitorBarChartVO {

    @Schema(description = "路段坑洼数量")
    private List<BarChartItemVO> potholeChart;

    @Schema(description = "路段裂缝长度")
    private List<BarChartItemVO> crackChart;

    @Schema(description = "区域交通流量峰值")
    private List<BarChartItemVO> trafficFlowChart;

}
