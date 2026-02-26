package cn.iocoder.yudao.module.park.controller.admin.park.trade.recover.vo;

import cn.iocoder.yudao.module.park.dal.dataobject.park.order.ordertemp.OrderTempDO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Schema(description = "追缴服务 - 钱包支付 Req VO")
public class RecoverWalletPayReqVO {

//    @Schema(description = "钱包支付密码")
//    @NotEmpty(message = "支付密码不能为空")
//    private String payPassword;

//    @Schema(description = "全部支付金额")
//    @NotNull(message = "全部支付金额不能为空")
//    private BigDecimal payAmount;

//    @Schema(description = "订单ID列表字符串")
//    @NotEmpty(message = "订单列ID表字符串不能为空")
//    private String orderIdListStr;

    @Schema(description = "订单对象")
    @NotNull(message = "订单对象列表不能为空")
    private List<OrderTempDO> orderTempDOList;
    //优惠券ID列表从订单列表获取
//    @Schema(description = "使用的优惠券ID，可为空")
//    private Long couponId;
}
