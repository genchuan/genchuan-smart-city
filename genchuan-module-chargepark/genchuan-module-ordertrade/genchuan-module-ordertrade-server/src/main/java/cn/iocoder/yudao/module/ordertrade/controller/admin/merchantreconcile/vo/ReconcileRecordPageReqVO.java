package cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 对账记录分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ReconcileRecordPageReqVO extends PageParam {

    @Schema(description = "所属对账单ID")
    private Long billId;

    @Schema(description = "对账单号")
    private String billNo;

    @Schema(description = "订单编号")
    private String orderNo;

    @Schema(description = "商户ID")
    private Long merchantId;

    @Schema(description = "对账结果：matched/unmatched/only_sys/only_merchant")
    private String matchResult;
}
