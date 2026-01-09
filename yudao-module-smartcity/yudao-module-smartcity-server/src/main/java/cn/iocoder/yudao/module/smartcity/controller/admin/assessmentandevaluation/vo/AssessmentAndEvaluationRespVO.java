package cn.iocoder.yudao.module.smartcity.controller.admin.assessmentandevaluation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 养护考核评价 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssessmentAndEvaluationRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "14192")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "养护人员编号")
    @ExcelProperty("养护人员编号")
    private String maintenancePersonnelNumber;

    @Schema(description = "养护任务编号")
    @ExcelProperty("养护任务编号")
    private String maintenanceTaskNumber;

    @Schema(description = "考核周期")
    @ExcelProperty("考核周期")
    private String assessmentCycle;

    @Schema(description = "考核得分")
    @ExcelProperty("考核得分")
    private String assessmentScore;

    @Schema(description = "考核等级")
    @ExcelProperty("考核等级")
    private String assessmentLevel;

    @Schema(description = "评价意见")
    @ExcelProperty("评价意见")
    private String evaluationOpinion;

    @Schema(description = "改进建议")
    @ExcelProperty("改进建议")
    private String improvementSuggestions;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}