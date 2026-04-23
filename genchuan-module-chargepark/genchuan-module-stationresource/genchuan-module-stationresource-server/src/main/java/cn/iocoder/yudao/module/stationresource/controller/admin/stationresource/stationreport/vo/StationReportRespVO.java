package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo;


import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 场站资源报表 Response VO
 */
@Data
@Schema(description = "场站资源报表")
public class StationReportRespVO {

    @ExcelProperty("报表ID")
    @Schema(description = "报表记录ID")
    private Long id;

    @ExcelProperty("报表周期")
    @Schema(description = "报表周期")
    private String reportCycle;

    @ExcelProperty("报表开始时间")
    @Schema(description = "开始时间")
    private LocalDateTime reportStartTime;

    @ExcelProperty("报表结束时间")
    @Schema(description = "结束时间")
    private LocalDateTime reportEndTime;

    @ExcelProperty("总片区数")
    @Schema(description = "总片区数")
    private Long totalAreaCount;

    @ExcelProperty("覆盖场站数")
    @Schema(description = "覆盖场站数")
    private Long coverStationCount;

    @ExcelProperty("总站场数")
    @Schema(description = "总站场数")
    private Long totalStationCount;

    @ExcelProperty("正常运营场站数")
    @Schema(description = "正常运营场站数")
    private Long normalOperateCount;

    @ExcelProperty("总车位数")
    @Schema(description = "总车位数")
    private Long totalSpaceCount;

    @ExcelProperty("可用车位数")
    @Schema(description = "可用车位数")
    private Long availableSpaceCount;

    @ExcelProperty("生效规则数")
    @Schema(description = "生效规则数")
    private Long effectiveRuleCount;

    @ExcelProperty("订单量")
    @Schema(description = "订单量")
    private Long orderCount;

    @ExcelProperty("营收金额")
    @Schema(description = "营收")
    private BigDecimal revenue;

    @ExcelProperty("追缴完成率(%)")
    @Schema(description = "追缴完成率")
    private BigDecimal recoveryRate;

    @ExcelProperty("押金订单量")
    @Schema(description = "押金订单量")
    private Long depositOrderCount;

    @ExcelProperty("生成状态")
    @Schema(description = "生成状态")
    private String generateStatus;

    @ExcelProperty("生成时间")
    @Schema(description = "生成时间")
    private LocalDateTime generateTime;

    @ExcelProperty("操作人")
    @Schema(description = "操作人")
    private String operator;

    @ExcelProperty("导出次数")
    @Schema(description = "导出次数")
    private Long exportCount;

    @ExcelProperty("创建时间")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("更新时间")
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
