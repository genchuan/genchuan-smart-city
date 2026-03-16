package cn.iocoder.yudao.module.facility.controller.admin.road.roadmonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "柱状图数据")
public class BarChartItemVO {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "值")
    private Double value;

}
