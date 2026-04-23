package cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.ordertrade.enums.PayOrderStatusEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.IntegerEnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.IntegerEnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 支付订单 Response VO")
@Data
public class PayOrderRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "应用ID")
    @ExcelProperty("应用ID")
    private Long appId;

    @Schema(description = "渠道ID")
    @ExcelProperty("渠道ID")
    private Long channelId;

    @Schema(description = "渠道编码")
    @ExcelProperty("渠道编码")
    private String channelCode;

    @Schema(description = "用户ID")
    @ExcelProperty("用户ID")
    private Long userId;

    @Schema(description = "用户类型")
    @ExcelProperty("用户类型")
    private Integer userType;

    @Schema(description = "商户订单号")
    @ExcelProperty("商户订单号")
    private String merchantOrderId;

    @Schema(description = "商品标题")
    @ExcelProperty("商品标题")
    private String subject;

    @Schema(description = "商品描述")
    @ExcelProperty("商品描述")
    private String body;

    @Schema(description = "异步通知地址")
    @ExcelProperty("异步通知地址")
    private String notifyUrl;

    @Schema(description = "支付金额（分）")
    @ExcelProperty("支付金额（分）")
    private Integer price;

    @Schema(description = "渠道手续费率（%）")
    @ExcelProperty("渠道手续费率（%）")
    private Double channelFeeRate;

    @Schema(description = "渠道手续金额（分）")
    @ExcelProperty("渠道手续金额（分）")
    private Integer channelFeePrice;

    @Schema(description = "状态")
    @ExcelProperty(value = "状态", converter = IntegerEnumExcelConverter.class)
    @IntegerEnumFormat(PayOrderStatusEnum.class)
    private Integer status;

    @Schema(description = "用户IP")
    @ExcelProperty("用户IP")
    private String userIp;

    @Schema(description = "过期时间")
    @ExcelProperty("过期时间")
    private LocalDateTime expireTime;

    @Schema(description = "支付成功时间")
    @ExcelProperty("支付成功时间")
    private LocalDateTime successTime;

    @Schema(description = "支付成功订单拓展单ID")
    @ExcelProperty("支付成功订单拓展单ID")
    private Long extensionId;

    @Schema(description = "支付成功外部订单号")
    @ExcelProperty("支付成功外部订单号")
    private String no;

    @Schema(description = "退款总金额（分）")
    @ExcelProperty("退款总金额（分）")
    private Integer refundPrice;

    @Schema(description = "渠道用户编号")
    @ExcelProperty("渠道用户编号")
    private String channelUserId;

    @Schema(description = "渠道订单号")
    @ExcelProperty("渠道订单号")
    private String channelOrderNo;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
