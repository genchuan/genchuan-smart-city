package cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 发票列表分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InvoiceListPageReqVO extends PageParam {

    @Schema(description = "发票编号，模糊查询")
    private String invoiceNo;

    @Schema(description = "关联订单ID")
    private Long orderId;

    @Schema(description = "发票抬头，模糊查询")
    private String title;

    @Schema(description = "状态：pending_audit/pending_invoice/invoiced/rejected")
    private String status;
}
