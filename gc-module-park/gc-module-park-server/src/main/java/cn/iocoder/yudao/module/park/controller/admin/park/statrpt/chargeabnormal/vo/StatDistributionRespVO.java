package cn.iocoder.yudao.module.park.controller.admin.park.statrpt.chargeabnormal.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "统计分布通用返回 VO")
@Data
public class StatDistributionRespVO {

    @Schema(description = "分组字段（可选，枚举型可用）", example = "NET_DELAY")
    private String groupFieldName;

    @Schema(description = "分类字段显示值", example = "网络延迟导致重复计费")
    private String groupFieldValueName;

    @Schema(description = "统计值（笔数 / 次数 / 金额）", example = "2")
    private BigDecimal value;

    @Schema(description = "占比（0~100）", example = "20.00")
    private BigDecimal ratio;
}
