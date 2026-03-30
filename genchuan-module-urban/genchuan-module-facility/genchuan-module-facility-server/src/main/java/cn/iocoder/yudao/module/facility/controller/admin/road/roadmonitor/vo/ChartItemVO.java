package cn.iocoder.yudao.module.facility.controller.admin.road.roadmonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "图表统计VO")
public class ChartItemVO {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "值")
    private Long value;

    @Schema(description = "占比(%)")
    private BigDecimal rate;

}
