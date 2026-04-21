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

    @Schema(description = "商户订单号")
    @ExcelProperty("商户订单号")
    private String merchantOrderId;

    @Schema(description = "渠道订单号")
    @ExcelProperty("渠道订单号")
    private String channelOrderNo;

    @Schema(description = "应用ID")
    @ExcelProperty("应用ID")
    private Long appId;

    @Schema(description = "渠道编码")
    @ExcelProperty("渠道编码")
    private String channelCode;

    @Schema(description = "金额（分）")
    @ExcelProperty("金额（分）")
    private Integer amount;

    @Schema(description = "状态")
    @ExcelProperty(value = "状态", converter = IntegerEnumExcelConverter.class)
    @IntegerEnumFormat(PayRefundStatusEnum.class)
    private Integer status;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
