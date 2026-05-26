package cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 代付订单分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AgentOrderPageReqVO extends PageParam {

    @Schema(description = "订单编号，模糊查询")
    private String orderNo;

    @Schema(description = "商户ID")
    private Long merchantId;

    @Schema(description = "商户名称，模糊查询")
    private String merchantName;

    @Schema(description = "车牌，模糊查询")
    private String carNo;

    @Schema(description = "支付方式：wechat/alipay/bank")
    private String payType;

    @Schema(description = "状态：pending_pay/paid/completed/cancelled")
    private String status;
}
