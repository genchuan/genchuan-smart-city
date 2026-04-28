package cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周期报表 Response VO")
@Data
public class CycleReportRespVO {

    @Schema(description = "报表记录ID")
    private Long id;

    @Schema(description = "报表周期")
    @ExcelProperty("报表周期")
    private String reportCycle;

    @Schema(description = "统计开始时间")
    @ExcelProperty("统计开始时间")
    private LocalDateTime statStartTime;

    @Schema(description = "统计结束时间")
    @ExcelProperty("统计结束时间")
    private LocalDateTime statEndTime;

    @Schema(description = "场站ID")
    private Long stationId;

    @Schema(description = "场站名称")
    @ExcelProperty("场站名称")
    private String stationName;

    @Schema(description = "入场量")
    @ExcelProperty("入场量")
    private Integer enterCount;

    @Schema(description = "离场量")
    @ExcelProperty("离场量")
    private Integer leaveCount;

    @Schema(description = "在停车辆数")
    @ExcelProperty("在停车辆数")
    private Integer parkingCount;

    @Schema(description = "识别成功率（%）")
    @ExcelProperty("识别成功率（%）")
    private BigDecimal identifySuccessRate;

    @Schema(description = "核验成功率（%）")
    @ExcelProperty("核验成功率（%）")
    private BigDecimal checkSuccessRate;

    @Schema(description = "异常处置率（%）")
    @ExcelProperty("异常处置率（%）")
    private BigDecimal abnormalHandleRate;

    @Schema(description = "ETC通行成功率（%）")
    @ExcelProperty("ETC通行成功率（%）")
    private BigDecimal etcPassSuccessRate;

    @Schema(description = "报表状态")
    @ExcelProperty("报表状态")
    private String reportStatus;

    @Schema(description = "报表生成时间")
    @ExcelProperty("生成时间")
    private LocalDateTime createTime;

    @Schema(description = "报表生成耗时（秒）")
    @ExcelProperty("生成耗时(秒)")
    private Integer createCost;

    @Schema(description = "数据更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "操作人")
    @ExcelProperty("操作人")
    private String creator;

}