package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderrefund.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 订单退款 Response VO")
@Data
@ExcelIgnoreUnannotated
public class OrderRefundRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "退款编号")
    @ExcelProperty("退款编号")
    private String refundCode;

    @Schema(description = "订单编号")
    @ExcelProperty("订单编号")
    private String orderCode;

    @Schema(description = "用户ID")
    @ExcelProperty("用户ID")
    private String userId;

    @Schema(description = "车牌号")
    @ExcelProperty("车牌号")
    private String plateNo;

    @Schema(description = "退款金额")
    @ExcelProperty("退款金额")
    private BigDecimal refundAmount;

    @Schema(description = "退款原因")
    @ExcelProperty("退款原因")
    private String refundReason;

    @Schema(description = "退款状态")
    @ExcelProperty("退款状态")
    private String refundStatus;

    @Schema(description = "审核人员")
    @ExcelProperty("审核人员")
    private String auditUser;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "审核备注")
    @ExcelProperty("审核备注")
    private String auditRemark;

    @Schema(description = "退款时间")
    @ExcelProperty("退款时间")
    private LocalDateTime refundTime;

    @Schema(description = "退款渠道")
    @ExcelProperty("退款渠道")
    private String refundChannel;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String creator;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}