package cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.ordertrade.enums.RefundRecordStatusEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 退款记录 Response VO")
@Data
public class RefundRecordRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "记录编号")
    @ExcelProperty("记录编号")
    private String recordNo;

    @Schema(description = "关联退款申请ID")
    @ExcelProperty("关联退款申请ID")
    private Long applyId;

    @Schema(description = "关联订单ID")
    @ExcelProperty("关联订单ID")
    private Long orderId;

    @Schema(description = "退款金额")
    @ExcelProperty("退款金额")
    private BigDecimal refundAmount;

    @Schema(description = "退款时间")
    @ExcelProperty("退款时间")
    private LocalDateTime refundTime;

    @Schema(description = "状态")
    @ExcelProperty(value = "状态", converter = EnumExcelConverter.class)
    @EnumFormat(RefundRecordStatusEnum.class)
    private String status;

    @Schema(description = "核查理由")
    @ExcelProperty("核查理由")
    private String checkReason;

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
