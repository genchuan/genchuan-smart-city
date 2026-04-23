package cn.iocoder.yudao.module.ordertrade.controller.admin.orderreport.vo;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周期报表 Response VO")
@Data
public class CycleReportRespVO {

    @Schema(description = "报表记录 ID")
    private Long id;

    @Schema(description = "报表周期（日报/周报/月报/季报/半年报/年报/自定义报表）")
    @ExcelProperty("报表周期")
    private String reportCycle;

    @Schema(description = "统计时段，如 2026-04-22 00:00:00-2026-04-22 23:59:59")
    @ExcelProperty("统计时段")
    private String statTime;

    @Schema(description = "周期订单量")
    @ExcelProperty("周期订单量")
    private Integer cycleOrderCount;

    @Schema(description = "周期营收（元）")
    @ExcelProperty("周期营收（元）")
    private BigDecimal cycleRevenue;

    @Schema(description = "支付率（%）")
    @ExcelProperty("支付率（%）")
    private BigDecimal payRate;

    @Schema(description = "充电量（度）")
    @ExcelProperty("充电量（度）")
    private BigDecimal chargeQuantity;

    @Schema(description = "借出量（次）")
    @ExcelProperty("借出量（次）")
    private Integer lendCount;

    @Schema(description = "退款金额（元）")
    @ExcelProperty("退款金额（元）")
    private BigDecimal refundAmount;

    @Schema(description = "待处置异常数")
    @ExcelProperty("待处置异常数")
    private Integer waitHandleAbnormalCount;

    @Schema(description = "追缴完成率（%）")
    @ExcelProperty("追缴完成率（%）")
    private BigDecimal collectCompleteRate;

    @Schema(description = "核算准确率（%）")
    @ExcelProperty("核算准确率（%）")
    private BigDecimal checkAccuracyRate;

    @Schema(description = "报表生成状态")
    @ExcelProperty("生成状态")
    private String generateStatus;

    @Schema(description = "报表生成时间")
    @ExcelProperty("生成时间")
    private LocalDateTime createTime;

    @Schema(description = "操作人")
    @ExcelProperty("操作人")
    private String operator;

    @Schema(description = "同比（与去年同期对比，如 +12.5%）")
    @ExcelProperty("同比")
    private String yearOnYear;

    @Schema(description = "环比（与上一周期对比，如 +8.3%）")
    @ExcelProperty("环比")
    private String chainRatio;

    @Schema(description = "报表导出次数")
    @ExcelProperty("导出次数")
    private Integer exportCount;
}
