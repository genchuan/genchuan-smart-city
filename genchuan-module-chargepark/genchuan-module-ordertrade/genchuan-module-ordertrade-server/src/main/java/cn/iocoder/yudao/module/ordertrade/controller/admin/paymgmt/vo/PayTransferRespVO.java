package cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.ordertrade.enums.PayTransferStatusEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.IntegerEnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.IntegerEnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 转账订单 Response VO")
@Data
public class PayTransferRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "转账单号")
    @ExcelProperty("转账单号")
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

    @Schema(description = "用户ID")
    @ExcelProperty("用户ID")
    private Long userId;

    @Schema(description = "用户类型")
    @ExcelProperty("用户类型")
    private Integer userType;

    @Schema(description = "商户转账单号")
    @ExcelProperty("商户转账单号")
    private String merchantTransferId;

    @Schema(description = "转账标题")
    @ExcelProperty("转账标题")
    private String subject;

    @Schema(description = "转账金额（分）")
    @ExcelProperty("转账金额（分）")
    private Integer price;

    @Schema(description = "收款人账号")
    @ExcelProperty("收款人账号")
    private String userAccount;

    @Schema(description = "收款人姓名")
    @ExcelProperty("收款人姓名")
    private String userName;

    @Schema(description = "状态")
    @ExcelProperty(value = "状态", converter = IntegerEnumExcelConverter.class)
    @IntegerEnumFormat(PayTransferStatusEnum.class)
    private Integer status;

    @Schema(description = "转账成功时间")
    @ExcelProperty("转账成功时间")
    private LocalDateTime successTime;

    @Schema(description = "异步通知地址")
    @ExcelProperty("异步通知地址")
    private String notifyUrl;

    @Schema(description = "用户IP")
    @ExcelProperty("用户IP")
    private String userIp;

    @Schema(description = "渠道转账单号")
    @ExcelProperty("渠道转账单号")
    private String channelTransferNo;

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
