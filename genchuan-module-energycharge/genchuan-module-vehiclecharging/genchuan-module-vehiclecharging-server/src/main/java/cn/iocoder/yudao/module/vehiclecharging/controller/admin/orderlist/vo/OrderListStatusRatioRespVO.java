package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderlist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 订单状态占比响应 VO")
@Data
public class OrderListStatusRatioRespVO {

    @Schema(description = "订单状态", example = "已完成")
    private String status;

    @Schema(description = "订单数量", example = "1200")
    private Integer count;

    @Schema(description = "占比（%）", example = "92.31")
    private BigDecimal ratio;
}