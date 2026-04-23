package cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.ordertrade.enums.InvoiceAuditStatusEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 开票审核 Response VO")
@Data
public class InvoiceAuditRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "关联发票ID")
    @ExcelProperty("关联发票ID")
    private Long applyId;

    @Schema(description = "关联发票编号")
    @ExcelProperty("关联发票编号")
    private String invoiceNo;

    @Schema(description = "申请人ID")
    @ExcelProperty("申请人ID")
    private Long applicantId;

    @Schema(description = "申请人名称")
    @ExcelProperty("申请人名称")
    private String applicantName;

    @Schema(description = "申请时间")
    @ExcelProperty("申请时间")
    private LocalDateTime applyTime;

    @Schema(description = "状态")
    @ExcelProperty(value = "状态", converter = EnumExcelConverter.class)
    @EnumFormat(InvoiceAuditStatusEnum.class)
    private String status;

    @Schema(description = "审核人ID")
    @ExcelProperty("审核人ID")
    private Long auditorId;

    @Schema(description = "审核人名称")
    @ExcelProperty("审核人名称")
    private String auditorName;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "审核结果")
    @ExcelProperty("审核结果")
    private String auditResult;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
