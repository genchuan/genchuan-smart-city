package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.decisionanalysis.stationopreport.vo;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "场站运营报表 Response VO")
@Data
public class StationOpReportRespVO {

    @ExcelProperty("主键ID")
    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @ExcelProperty("报表类型")
    @Schema(description = "报表类型", example = "月报")
    private String reportType;

    @ExcelProperty("报表统计时间标识")
    @Schema(description = "报表统计时间标识", example = "2025-03")
    private String reportTime;

    @ExcelProperty("开始时间")
    @Schema(description = "开始时间戳(毫秒)", example = "1775011986000")
    private LocalDateTime startTime;

    @ExcelProperty("结束时间")
    @Schema(description = "结束时间戳(毫秒)", example = "1777603986000")
    private LocalDateTime endTime;

    @ExcelProperty("创建者")
    @Schema(description = "创建者", example = "system")
    private String creator;

    @ExcelProperty("创建时间")
    @Schema(description = "创建时间", example = "1777603986000")
    private LocalDateTime createTime;

    // ========== 扩展展示字段 ==========
    @ExcelProperty("场站数")
    @Schema(description = "场站数", example = "20")
    private Integer stationCount;

    @ExcelProperty("车位使用率")
    @Schema(description = "车位使用率", example = "85.50")
    private BigDecimal spaceUseRate;

    @ExcelProperty("规则匹配率")
    @Schema(description = "规则匹配率", example = "98.00")
    private BigDecimal ruleMatchRate;

    @ExcelProperty("总车位数")
    @Schema(description = "总车位数", example = "500")
    private Integer totalSpaceCount;

    @ExcelProperty("营收")
    @Schema(description = "营收", example = "150000.00")
    private BigDecimal revenue;

    @ExcelProperty("同比")
    @Schema(description = "同比", example = "10.20")
    private BigDecimal yearOnYear;

    @ExcelProperty("环比")
    @Schema(description = "环比", example = "5.30")
    private BigDecimal monthOnMonth;
}
