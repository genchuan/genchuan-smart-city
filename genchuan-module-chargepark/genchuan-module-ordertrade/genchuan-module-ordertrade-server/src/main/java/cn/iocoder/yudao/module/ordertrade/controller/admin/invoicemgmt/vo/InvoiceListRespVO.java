package cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.ordertrade.enums.InvoiceListStatusEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 发票列表 Response VO")
@Data
public class InvoiceListRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "发票编号")
    @ExcelProperty("发票编号")
    private String invoiceNo;

    /*@Schema(description = "关联订单ID")
    @ExcelProperty("关联订单ID")
    private Long orderId;*/

    @Schema(description = "关联订单编号")
    @ExcelProperty("关联订单编号")
    private String orderNo;

    @Schema(description = "发票抬头")
    @ExcelProperty("发票抬头")
    private String title;

    @Schema(description = "税号")
    @ExcelProperty("税号")
    private String taxNo;

    @Schema(description = "开票金额")
    @ExcelProperty("开票金额")
    private BigDecimal amount;

    @Schema(description = "状态")
    @ExcelProperty(value = "状态", converter = EnumExcelConverter.class)
    @EnumFormat(InvoiceListStatusEnum.class)
    private String status;

   /* @Schema(description = "审核人ID")
    @ExcelProperty("审核人ID")
    private Long auditorId;*/

    @Schema(description = "审核人名称")
    @ExcelProperty("审核人名称")
    private String updater;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "开票时间")
    @ExcelProperty("开票时间")
    private LocalDateTime invoiceTime;

    @Schema(description = "推送时间")
    @ExcelProperty("推送时间")
    private LocalDateTime pushTime;

    @Schema(description = "下载链接")
    @ExcelProperty("下载链接")
    private String downloadUrl;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("邮箱")
    private String reserve1;

   /* @Schema(description = "备用字段2")
    private String reserve2;*/

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;
}
