package cn.iocoder.yudao.module.park.controller.admin.park.statrpt.chargeabnormal.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 趋势点 VO")
@Data
public class TrendPointVO {

    @Schema(description = "[时间标识] 按统计粒度格式化后的时间，如：2026-01-01 / 2026-01-01 10", example = "2026-01-01")
    private String timeKey;

    @Schema(description = "[时间粒度] DAY-天 / HOUR-小时 / MONTH-月", example = "DAY")
    private String granularity;

    @Schema(description = "[统计值] 对应时间点的统计结果", example = "1234.56")
    private BigDecimal value;
}
