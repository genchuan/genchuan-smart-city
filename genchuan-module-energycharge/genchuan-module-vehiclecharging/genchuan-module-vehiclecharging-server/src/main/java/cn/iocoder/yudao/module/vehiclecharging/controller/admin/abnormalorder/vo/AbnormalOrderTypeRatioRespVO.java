package cn.iocoder.yudao.module.vehiclecharging.controller.admin.abnormalorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "汽车充电 - 异常订单类型占比 Response VO")
@Data
public class AbnormalOrderTypeRatioRespVO {

    @Schema(description = "异常类型", example = "充电中断")
    private String type;

    @Schema(description = "数量", example = "15")
    private Integer count;

    @Schema(description = "占比 %", example = "46.88")
    private BigDecimal ratio;
}