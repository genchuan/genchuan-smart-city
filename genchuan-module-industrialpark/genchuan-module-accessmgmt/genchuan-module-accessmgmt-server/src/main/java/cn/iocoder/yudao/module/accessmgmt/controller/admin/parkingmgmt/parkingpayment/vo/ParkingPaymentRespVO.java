package cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingpayment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import cn.idev.excel.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 停车缴费 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkingPaymentRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "账单编号")
    @ExcelProperty("账单编号")
    private String billCode;

    @Schema(description = "车牌号")
    @ExcelProperty("车牌号")
    private String plateNo;

    @Schema(description = "停车时长（分钟）")
    @ExcelProperty("停车时长")
    private Integer parkDuration;

    @Schema(description = "费用金额")
    @ExcelProperty("费用金额")
    private BigDecimal feeAmount;

    @Schema(description = "账单状态（待缴费/已缴费/已欠费）")
    @ExcelProperty("账单状态")
    private String billStatus;

    @Schema(description = "支付方式（微信/支付宝/现金）")
    @ExcelProperty("支付方式")
    private String payType;

    @Schema(description = "支付时间")
    @ExcelProperty("支付时间")
    private LocalDateTime payTime;

    @Schema(description = "发票状态（已开具/未开具）")
    @ExcelProperty("发票状态")
    private String invoiceStatus;

    @Schema(description = "优惠金额")
    @ExcelProperty("优惠金额")
    private BigDecimal discountAmount;

    @Schema(description = "优惠原因")
    @ExcelProperty("优惠原因")
    private String discountReason;

    @Schema(description = "操作人账号")
    @ExcelProperty("操作人账号")
    private String handleUser;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

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
