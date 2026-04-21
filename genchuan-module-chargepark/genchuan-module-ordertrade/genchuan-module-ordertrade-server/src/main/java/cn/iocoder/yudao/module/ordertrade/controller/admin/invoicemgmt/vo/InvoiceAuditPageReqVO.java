package cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 开票审核分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InvoiceAuditPageReqVO extends PageParam {

    @Schema(description = "关联发票ID")
    private Long applyId;

    @Schema(description = "申请人ID")
    private Long applicantId;

    @Schema(description = "状态：pending/approved/rejected")
    private String status;
}
