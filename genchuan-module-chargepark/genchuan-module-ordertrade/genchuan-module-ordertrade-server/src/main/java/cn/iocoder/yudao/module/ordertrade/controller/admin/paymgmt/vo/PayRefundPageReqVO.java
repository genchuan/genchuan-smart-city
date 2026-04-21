package cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 退款订单分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PayRefundPageReqVO extends PageParam {

    @Schema(description = "应用ID")
    private Long appId;

    @Schema(description = "渠道编码")
    private String channelCode;

    @Schema(description = "状态：0待退款/10退款中/20退款成功/30退款失败")
    private Integer status;
}
