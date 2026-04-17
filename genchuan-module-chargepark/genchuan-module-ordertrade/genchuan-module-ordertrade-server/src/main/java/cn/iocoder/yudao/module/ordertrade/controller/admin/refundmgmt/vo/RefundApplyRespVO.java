package cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 退款申请 Response VO")
@Data
public class RefundApplyRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "申请编号")
    @ExcelProperty("申请编号")
    private String applyNo;
    @Schema(description = "关联订单ID")
    @ExcelProperty("关联订单ID")
    private Long orderId;
    @Schema(description = "退款金额")
    @ExcelProperty("退款金额")
    private BigDecimal refundAmount;
    @Schema(description = "退款原因")
    @ExcelProperty("退款原因")
    private String refundReason;
    @Schema(description = "申请时间")
    @ExcelProperty("申请时间")
    private LocalDateTime applyTime;
    @Schema(description = "状态")
    @ExcelProperty("状态")
    private String status;
    @Schema(description = "申请人ID")
    @ExcelProperty("申请人ID")
    private Long applicantId;
    @Schema(description = "审核人ID")
    @ExcelProperty("审核人ID")
    private Long auditUserId;
    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;
    @Schema(description = "操作人ID")
    @ExcelProperty("操作人ID")
    private Long operatorId;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
