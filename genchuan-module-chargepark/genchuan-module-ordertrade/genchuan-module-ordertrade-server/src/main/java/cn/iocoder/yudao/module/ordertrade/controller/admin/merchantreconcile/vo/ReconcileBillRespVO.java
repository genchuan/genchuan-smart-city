package cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.ordertrade.enums.ReconcileBillStatusEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 商户对账单 Response VO")
@Data
public class ReconcileBillRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "对账单号")
    @ExcelProperty("对账单号")
    private String billNo;

    @Schema(description = "商户ID")
    @ExcelProperty("商户ID")
    private Long merchantId;

    @Schema(description = "商户名称")
    @ExcelProperty("商户名称")
    private String merchantName;

    @Schema(description = "对账日期")
    @ExcelProperty("对账日期")
    private LocalDate billDate;

    @Schema(description = "系统订单总金额")
    @ExcelProperty("系统订单总金额")
    private BigDecimal sysAmount;

    @Schema(description = "商户上报总金额")
    @ExcelProperty("商户上报总金额")
    private BigDecimal merchantAmount;

    @Schema(description = "差异金额")
    @ExcelProperty("差异金额")
    private BigDecimal diffAmount;

    @Schema(description = "对账状态")
    @ExcelProperty(value = "对账状态", converter = EnumExcelConverter.class)
    @EnumFormat(ReconcileBillStatusEnum.class)
    private String status;

    @Schema(description = "确认时间")
    @ExcelProperty("确认时间")
    private LocalDateTime confirmTime;

    @Schema(description = "操作人ID")
    @ExcelProperty("操作人ID")
    private Long operatorId;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;
}
