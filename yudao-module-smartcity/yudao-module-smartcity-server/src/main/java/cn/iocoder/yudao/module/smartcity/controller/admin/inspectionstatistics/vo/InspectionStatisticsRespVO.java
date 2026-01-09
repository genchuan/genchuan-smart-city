package cn.iocoder.yudao.module.smartcity.controller.admin.inspectionstatistics.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查分析统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectionStatisticsRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "19334")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "巡查区域")
    @ExcelProperty("巡查区域")
    private String patrolArea;

    @Schema(description = "巡查人员")
    @ExcelProperty("巡查人员")
    private String patrolPersonnel;

    @Schema(description = "巡查任务完成率")
    @ExcelProperty("巡查任务完成率")
    private String completionInspectionTasks;

    @Schema(description = "平均巡查时长")
    @ExcelProperty("平均巡查时长")
    private String averagePatrolDuration;

    @Schema(description = "问题发现数量")
    @ExcelProperty("问题发现数量")
    private String numberProblemDiscoveries;

    @Schema(description = "不同类型问题分布")
    @ExcelProperty("不同类型问题分布")
    private String distributionProblems;

    @Schema(description = "问题解决率")
    @ExcelProperty("问题解决率")
    private String problemSolvingRate;

    @Schema(description = "重复问题发生率")
    @ExcelProperty("重复问题发生率")
    private String repetitiveProblemRate;

    @Schema(description = "风险等级评估")
    @ExcelProperty("风险等级评估")
    private String riskLevelAssessment;

    @Schema(description = "建议与改进措施")
    @ExcelProperty("建议与改进措施")
    private String suggestionsMeasures;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}