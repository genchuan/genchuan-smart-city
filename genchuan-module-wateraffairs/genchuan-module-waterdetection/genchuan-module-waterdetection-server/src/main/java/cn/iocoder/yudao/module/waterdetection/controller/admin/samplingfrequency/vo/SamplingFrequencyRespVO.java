package cn.iocoder.yudao.module.waterdetection.controller.admin.samplingfrequency.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 采样频率设置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SamplingFrequencyRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "采样点编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("采样点编号")
    private String pointCode;

    @Schema(description = "指标名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("指标名称")
    private String indicatorName;

    @Schema(description = "采样频率(次/月/季)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("采样频率(次/月/季)")
    private String frequency;

    @Schema(description = "执行周期")
    @ExcelProperty("执行周期")
    private String executionCycle;

    @Schema(description = "特殊时段(如汛期)调整规则")
    @ExcelProperty("特殊时段(如汛期)调整规则")
    private String specialPeriodRule;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}