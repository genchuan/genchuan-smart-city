package cn.iocoder.yudao.module.smartcity.controller.admin.environmentalsanitation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 市容环卫 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EnvironmentalSanitationRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "29302")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "设施名称", example = "王五")
    @ExcelProperty("设施名称")
    private String facilityName;

    @Schema(description = "设施位置")
    @ExcelProperty("设施位置")
    private String facilityLocation;

    @Schema(description = "数量")
    @ExcelProperty("数量")
    private String quantity;

    @Schema(description = "建设时间")
    @ExcelProperty("建设时间")
    private LocalDateTime constructionTime;

    @Schema(description = "使用年限")
    @ExcelProperty("使用年限")
    private String serviceLife;

    @Schema(description = "处理能力")
    @ExcelProperty("处理能力")
    private String processingCapacity;

    @Schema(description = "维护需求")
    @ExcelProperty("维护需求")
    private String maintenanceRequirements;

    @Schema(description = "年度预算金额")
    @ExcelProperty("年度预算金额")
    private String annualBudgetAmount;

    @Schema(description = "成本效益分析")
    @ExcelProperty("成本效益分析")
    private String costBenefitAnalysis;

    @Schema(description = "对周边环境影响评估")
    @ExcelProperty("对周边环境影响评估")
    private String assessmentSurEnvironment;

    @Schema(description = "节能减排指标")
    @ExcelProperty("节能减排指标")
    private String energyConservationTargets;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}