package cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 开票审核分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InvoiceAuditPageReqVO extends PageParam {

    @Schema(description = "申请人ID")
    private Long applicantId;

    @Schema(description = "创建人（申请人）")
    private String creator;

    @Schema(description = "审核状态：pending/approved/rejected")
    private String status;

    @Schema(description = "申请时间开始")
    private LocalDateTime applyTimeStart;

    @Schema(description = "申请时间结束")
    private LocalDateTime applyTimeEnd;
}
