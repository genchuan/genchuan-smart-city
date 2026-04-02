package cn.iocoder.yudao.module.vehiclecharging.controller.admin.ratesetting.vo.chart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Schema(description = "管理后台 - 费率状态统计 Response VO")
public class RateSettingStatusCountRespVO {

    @Schema(description = "总费率方案数", example = "8")
    private Integer totalRateCount;

    @Schema(description = "已生效方案数", example = "5")
    private Integer enableCount;

    @Schema(description = "未生效方案数", example = "2")
    private Integer unEnableCount;

    @Schema(description = "已失效方案数", example = "1")
    private Integer disableCount;

    @Schema(description = "生效占比（%）", example = "62.5")
    private BigDecimal enableRatio;
}
