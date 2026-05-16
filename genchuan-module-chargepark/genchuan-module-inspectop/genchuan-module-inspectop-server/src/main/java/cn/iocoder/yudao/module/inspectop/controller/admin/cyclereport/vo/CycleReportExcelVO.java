package cn.iocoder.yudao.module.inspectop.controller.admin.cyclereport.vo;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "巡检运维报表 Excel 导出 VO")
@Data
public class CycleReportExcelVO {

    @ExcelProperty("报表ID")
    @Schema(description = "报表主键 ID", example = "1")
    private Long id;

    @ExcelProperty("报表周期")
    @Schema(description = "报表周期", example = "月报")
    private String reportCycle;

    @ExcelProperty("统计开始时间")
    @Schema(description = "统计开始时间", example = "2026-01-01 00:00:00")
    private LocalDateTime statTimeStart;

    @ExcelProperty("统计结束时间")
    @Schema(description = "统计结束时间", example = "2026-01-31 23:59:59")
    private LocalDateTime statTimeEnd;

    @ExcelProperty("场站名称")
    @Schema(description = "所属场站名称", example = "泉州丰泽充电场站")
    private String stationName;

    @ExcelProperty("正常设备数")
    @Schema(description = "正常设备数", example = "156")
    private Integer normalDeviceNum;

    @ExcelProperty("异常设备数")
    @Schema(description = "异常设备数", example = "8")
    private Integer abnormalDeviceNum;

    @ExcelProperty("巡检任务数")
    @Schema(description = "巡检任务数", example = "240")
    private Integer inspectTaskNum;

    @ExcelProperty("任务完成率(%)")
    @Schema(description = "任务完成率", example = "98.5")
    private BigDecimal taskCompleteRate;

    @ExcelProperty("油车占位待处置数")
    @Schema(description = "油车占位待处置数", example = "12")
    private Integer oilWaitHandleNum;

    @ExcelProperty("处置完成率(%)")
    @Schema(description = "处置完成率", example = "95.2")
    private BigDecimal oilHandleCompleteRate;

    @ExcelProperty("巡检人员在岗数")
    @Schema(description = "巡检人员在岗数", example = "28")
    private Integer inspectUserOnlineNum;

    @ExcelProperty("资产正常数")
    @Schema(description = "资产正常数", example = "320")
    private Integer assetNormalNum;

    @ExcelProperty("库存预警数")
    @Schema(description = "库存预警数", example = "6")
    private Integer stockWarnNum;

    @ExcelProperty("生成状态")
    @Schema(description = "生成状态", example = "已生成")
    private String generateStatus;

    @ExcelProperty("报表生成时间")
    @Schema(description = "报表生成时间")
    private LocalDateTime generateTime;

    @ExcelProperty("操作人")
    @Schema(description = "操作人", example = "admin")
    private String operator;

    @ExcelProperty("报表导出次数")
    @Schema(description = "报表导出次数", example = "3")
    private Integer exportCount;

    @ExcelProperty("同比数据")
    @Schema(description = "同比数据", example = "同比增长 5.2%")
    private String yearOnYearData;

    @ExcelProperty("环比数据")
    @Schema(description = "环比数据", example = "环比增长 2.8%")
    private String chainRatioData;

    @ExcelProperty("创建时间")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("更新时间")
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}