package cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.coremetrics.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 应急核心指标 Response VO")
@Data
public class EmergCoreMetricsRespVO {

    @Schema(description = "应急事件办结率信息", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("应急事件办结率")
    private EmergEventCompleteRate emergEventCompleteRate;

    @Schema(description = "预警准确率信息", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预警准确率")
    private EarlyWarnAccRate earlyWarnAccRate;

    @Schema(description = "资源调用率信息", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("资源调用率")
    private ResUseRate resUseRate;

    @Schema(description = "风险整改率信息", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("风险整改率")
    private RiskRectifyRate riskRectifyRate;

    @Schema(description = "平均处置时长",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("平均处置时长")
    private AvgHandleEndure avgHandleEndure;



    @Data
    public static class EmergEventCompleteRate {

        @Schema(description = "办结率，单位：%", example = "85.5")
        @ExcelProperty("办结率")
        private BigDecimal rate;

        @Schema(description = "已办结事件数", example = "120")
        @ExcelProperty("已办结数")
        private Integer completedCount;

        @Schema(description = "事件总数", example = "150")
        @ExcelProperty("总事件数")
        private Integer totalCount;

        @Schema(description = "数据更新时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "2025-11-19 10:00:00")
        @ExcelProperty("数据更新时间")
        private LocalDateTime updateTime;

        @Schema(description = "异常预警阈值，低于此值标红", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "40")
        @ExcelProperty("异常预警阈值")
        private BigDecimal warningThreshold=new BigDecimal("40");

        @Schema(description = "近七天趋势")
        @ExcelProperty("近七天趋势")
        private List<TimeValuePoint> trendList;
    }

    @Data
    public static class EarlyWarnAccRate{
        @Schema(description = "准确率，单位：%", example = "85.5")
        @ExcelProperty("准确率")
        private BigDecimal rate;

        @Schema(description = "已处理事件数", example = "120")
        @ExcelProperty("已处理数")
        private Integer completedCount;

        @Schema(description = "事件总数", example = "150")
        @ExcelProperty("总事件数")
        private Integer totalCount;

        @Schema(description = "数据更新时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "2025-11-19 10:00:00")
        @ExcelProperty("数据更新时间")
        private LocalDateTime updateTime;

        @Schema(description = "异常预警阈值，低于此值标红", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "40")
        @ExcelProperty("异常预警阈值")
        private BigDecimal warningThreshold=new BigDecimal("40");

        @Schema(description = "近七天趋势")
        @ExcelProperty("近七天趋势")
        private List<TimeValuePoint> trendList;
    }

    @Data
    public static class ResUseRate {

        @Schema(description = "调用率，单位：%", example = "76.5")
        @ExcelProperty("调用率")
        private BigDecimal rate;

        @Schema(description = "已调用资源数", example = "15")
        @ExcelProperty("已调用数")
        private Integer usedCount;

        @Schema(description = "资源总数", example = "20")
        @ExcelProperty("总资源数")
        private Integer totalCount;

        @Schema(description = "数据更新时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "2025-11-19 10:00:00")
        @ExcelProperty("数据更新时间")
        private LocalDateTime updateTime;

        @Schema(description = "异常预警阈值，低于此值标红", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "40")
        @ExcelProperty("异常预警阈值")
        private BigDecimal warningThreshold=new BigDecimal("40");

        @Schema(description = "近七天趋势")
        @ExcelProperty("近七天趋势")
        private List<TimeValuePoint> trendList;
    }

    @Data
    public static class RiskRectifyRate {

        @Schema(description = "整改率，单位：%", example = "88.8")
        @ExcelProperty("整改率")
        private BigDecimal rate;

        @Schema(description = "已整改数", example = "12")
        @ExcelProperty("已整改数")
        private Integer rectifiedCount;

        @Schema(description = "总隐患数", example = "15")
        @ExcelProperty("总隐患数")
        private Integer totalCount;

        @Schema(description = "数据更新时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "2025-11-19 10:00:00")
        @ExcelProperty("数据更新时间")
        private LocalDateTime updateTime;

        @Schema(description = "异常预警阈值，低于此值标红", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "40")
        @ExcelProperty("异常预警阈值")
        private BigDecimal warningThreshold=new BigDecimal("40");

        @Schema(description = "近七天趋势")
        @ExcelProperty("近七天趋势")
        private List<TimeValuePoint> trendList;
    }
    @Data
    public static class AvgHandleEndure {

        @Schema(description = "平均处置时长", example = "5")
        @ExcelProperty("平均处置时长")
        private BigDecimal avgHandleDuration;

        @Schema(description = "数据更新时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "2025-11-19 10:00:00")
        @ExcelProperty("数据更新时间")
        private LocalDateTime updateTime;

        @Schema(description = "异常预警阈值，低于此值标红", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "40")
        @ExcelProperty("异常预警阈值")
        private BigDecimal warningThreshold=new BigDecimal("40");
    }




}
