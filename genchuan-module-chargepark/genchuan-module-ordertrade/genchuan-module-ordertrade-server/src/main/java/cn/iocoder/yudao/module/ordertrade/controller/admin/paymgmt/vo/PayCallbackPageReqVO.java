package cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 回调通知分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PayCallbackPageReqVO extends PageParam {

    @Schema(description = "应用ID")
    private Long appId;

    @Schema(description = "类型：1支付成功/2退款")
    private Integer type;

    @Schema(description = "状态：0待通知/10通知成功/20通知失败")
    private Integer status;
}
