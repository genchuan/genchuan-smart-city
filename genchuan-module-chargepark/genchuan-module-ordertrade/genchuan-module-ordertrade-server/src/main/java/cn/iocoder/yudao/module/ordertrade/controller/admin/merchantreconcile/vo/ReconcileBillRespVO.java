package cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.ordertrade.enums.ReconcileBillStatusEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
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

    @Schema(description = "对账周期")
    @ExcelProperty("对账周期")
    private String cycle;

    @Schema(description = "平台金额")
    @ExcelProperty("平台金额")
    private BigDecimal platformAmount;

    @Schema(description = "商户金额")
    @ExcelProperty("商户金额")
    private BigDecimal merchantAmount;

    @Schema(description = "对账状态")
    @ExcelProperty(value = "对账状态", converter = EnumExcelConverter.class)
    @EnumFormat(ReconcileBillStatusEnum.class)
    private String status;

    @Schema(description = "对账人ID")
    @ExcelProperty("对账人ID")
    private Long reconcilerId;

    @Schema(description = "对账时间")
    @ExcelProperty("对账时间")
    private LocalDateTime reconcileTime;

    @Schema(description = "确认时间")
    @ExcelProperty("确认时间")
    private LocalDateTime confirmTime;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;
}
