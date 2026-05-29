package cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.ordertrade.enums.PayRefundStatusEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.IntegerEnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.IntegerEnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 退款订单 Response VO")
@Data
public class PayRefundRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "外部退款号")
    @ExcelProperty("外部退款号")
    private String no;

    @Schema(description = "应用ID")
    @ExcelProperty("应用ID")
    private Long appId;

    @Schema(description = "渠道ID")
    @ExcelProperty("渠道ID")
    private Long channelId;

    @Schema(description = "渠道编码")
    @ExcelProperty("渠道编码")
    private String channelCode;

    @Schema(description = "支付订单ID")
    @ExcelProperty("支付订单ID")
    private Long orderId;

    @Schema(description = "支付订单号")
    @ExcelProperty("支付订单号")
    private String orderNo;

    @Schema(description = "用户ID")
    @ExcelProperty("用户ID")
    private Long userId;

    @Schema(description = "用户类型")
    @ExcelProperty("用户类型")
    private Integer userType;

    @Schema(description = "商户订单号")
    @ExcelProperty("商户订单号")
    private String merchantOrderId;

    @Schema(description = "商户退款单号")
    @ExcelProperty("商户退款单号")
    private String merchantRefundId;

    @Schema(description = "异步通知地址")
    @ExcelProperty("异步通知地址")
    private String notifyUrl;

    @Schema(description = "状态")
    @ExcelProperty(value = "状态", converter = IntegerEnumExcelConverter.class)
    @IntegerEnumFormat(PayRefundStatusEnum.class)
    private Integer status;

    @Schema(description = "支付金额（分）")
    @ExcelProperty("支付金额（分）")
    private Integer payPrice;

    @Schema(description = "退款金额（分）")
    @ExcelProperty("退款金额（分）")
    private Integer refundPrice;

    @Schema(description = "退款原因")
    @ExcelProperty("退款原因")
    private String reason;

    @Schema(description = "用户IP")
    @ExcelProperty("用户IP")
    private String userIp;

    @Schema(description = "渠道订单号")
    @ExcelProperty("渠道订单号")
    private String channelOrderNo;

    @Schema(description = "渠道退款单号")
    @ExcelProperty("渠道退款单号")
    private String channelRefundNo;

    @Schema(description = "退款成功时间")
    @ExcelProperty("退款成功时间")
    private LocalDateTime successTime;

    @Schema(description = "渠道错误码")
    @ExcelProperty("渠道错误码")
    private String channelErrorCode;

    @Schema(description = "渠道错误提示")
    @ExcelProperty("渠道错误提示")
    private String channelErrorMsg;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;
}
