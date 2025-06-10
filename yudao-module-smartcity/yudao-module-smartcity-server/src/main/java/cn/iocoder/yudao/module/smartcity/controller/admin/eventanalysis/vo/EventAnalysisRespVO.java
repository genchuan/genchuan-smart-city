package cn.iocoder.yudao.module.smartcity.controller.admin.eventanalysis.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 事件关联分析 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EventAnalysisRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "3923")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "关联分析编号")
    @ExcelProperty("关联分析编号")
    private String analysisNumber;

    @Schema(description = "主事件编号")
    @ExcelProperty("主事件编号")
    private String mainEventNumber;

    @Schema(description = "主事件名称", example = "赵六")
    @ExcelProperty("主事件名称")
    private String mainEventName;

    @Schema(description = "关联事件编号")
    @ExcelProperty("关联事件编号")
    private String relatedEventNumber;

    @Schema(description = "关联事件名称", example = "李四")
    @ExcelProperty("关联事件名称")
    private String relatedEventName;

    @Schema(description = "关联类型", example = "1")
    @ExcelProperty("关联类型")
    private String associationType;

    @Schema(description = "关联强度")
    @ExcelProperty("关联强度")
    private String associationStrength;

    @Schema(description = "分析时间")
    @ExcelProperty("分析时间")
    private LocalDateTime analysisTime;

    @Schema(description = "分析人员")
    @ExcelProperty("分析人员")
    private String analysts;

    @Schema(description = "分析结论")
    @ExcelProperty("分析结论")
    private String conclusion;

    @Schema(description = "关联证据")
    @ExcelProperty("关联证据")
    private String relatedEvidence;

    @Schema(description = "建议措施")
    @ExcelProperty("建议措施")
    private String recommendedMeasure;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}