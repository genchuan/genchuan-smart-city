package cn.iocoder.yudao.module.park.controller.admin.park.trade.recover.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
@Data
@Schema(description = "追缴服务 - 钱包支付 Req VO")
public class WalletPayOrderTempReqVO {
    @Schema(description = "订单对象id列表")
    @NotNull(message = "订单对象id列表不能为空")
    private List<Long> orderTempIdList;
}
