package cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.ordertrade.enums.ReconcileRecordStatusEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 对账记录 Response VO")
@Data
public class ReconcileRecordRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "所属对账单ID")
    @ExcelProperty("所属对账单ID")
    private Long billId;

    @Schema(description = "对账单号")
    @ExcelProperty("对账单号")
    private String billNo;

    @Schema(description = "商户ID")
    @ExcelProperty("商户ID")
    private Long merchantId;

    @Schema(description = "订单编号")
    @ExcelProperty("订单编号")
    private String orderNo;

    @Schema(description = "系统金额")
    @ExcelProperty("系统金额")
    private BigDecimal sysAmount;

    @Schema(description = "商户上报金额")
    @ExcelProperty("商户上报金额")
    private BigDecimal merchantAmount;

    @Schema(description = "差异金额")
    @ExcelProperty("差异金额")
    private BigDecimal diffAmount;

    @Schema(description = "对账结果")
    @ExcelProperty(value = "对账结果", converter = EnumExcelConverter.class)
    @EnumFormat(ReconcileRecordStatusEnum.class)
    private String matchResult;

    @Schema(description = "异常原因")
    @ExcelProperty("异常原因")
    private String diffReason;

    @Schema(description = "处理时间")
    @ExcelProperty("处理时间")
    private LocalDateTime handleTime;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;
}
