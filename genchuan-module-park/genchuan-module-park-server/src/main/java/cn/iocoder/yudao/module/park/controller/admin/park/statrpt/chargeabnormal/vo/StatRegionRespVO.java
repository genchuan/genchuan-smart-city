package cn.iocoder.yudao.module.park.controller.admin.park.statrpt.chargeabnormal.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "各区域 统计 返回 VO")
@Data
public class StatRegionRespVO {
    @Schema(description = "统计地区12位地址码", example = "350101000000")
    private String regionFullCode;

    @Schema(description = "统计地区 名称", example = "龙文区")
    private String regionName;

    @Schema(description = "统计值（笔数 / 次数 / 金额）", example = "2")
    private BigDecimal value;

    @Schema(description = "占比（0~100）", example = "20.00")
    private BigDecimal ratio;
}
