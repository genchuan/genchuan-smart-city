package cn.iocoder.yudao.module.studentmgmt.controller.admin.dutymgmt.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Schema(description = "管理后台 - 值班调度看板 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DutyMgmtChartRespVO {

    @Schema(description = "总值班次数")
    @ExcelProperty("总值班次数")
    private Integer totalDutyCount;
    @Schema(description = "今日值班人数")
    @ExcelProperty("今日值班人数")
    private Integer todayDutyCount;
    @Schema(description = "打卡率，百分比保留 2 位小数")
    @ExcelProperty("打卡率，百分比保留 2 位小数")
    private BigDecimal checkInRate;
    @Schema(description = "调班申请数")
    @ExcelProperty("调班申请数")
    private Integer shiftApplyCount;
    @Schema(description = "出车申请数")
    @ExcelProperty("出车申请数")
    private Integer vehicleApplyCount;
    @Schema(description = "各状态值班记录数量统计")
    @ExcelProperty("各状态值班记录数量统计")
    private Map<String, Integer> statusCountMap;


}
