package cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 支付订单分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PayOrderPageReqVO extends PageParam {

    @Schema(description = "商户订单号，模糊查询")
    private String merchantOrderId;

    @Schema(description = "应用ID")
    private Long appId;

    @Schema(description = "渠道编码")
    private String channelCode;

    @Schema(description = "状态：0待支付/10已支付/20退款中/30已退款/40已关闭")
    private Integer status;
}
