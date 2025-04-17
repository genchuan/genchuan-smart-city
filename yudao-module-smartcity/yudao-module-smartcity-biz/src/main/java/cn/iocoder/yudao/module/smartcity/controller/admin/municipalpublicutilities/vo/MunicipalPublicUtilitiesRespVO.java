package cn.iocoder.yudao.module.smartcity.controller.admin.municipalpublicutilities.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 市政公用 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MunicipalPublicUtilitiesRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "31565")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "市政设施名称")
    @ExcelProperty("市政设施名称")
    private String nameMunicipalFacilities;

    @Schema(description = "设施类型", example = "2")
    @ExcelProperty("设施类型")
    private String facilityType;

    @Schema(description = "设施位置")
    @ExcelProperty("设施位置")
    private String facilityLocation;

    @Schema(description = "建设时间")
    @ExcelProperty("建设时间")
    private LocalDateTime constructionTime;

    @Schema(description = "使用年限")
    @ExcelProperty("使用年限")
    private String serviceLife;

    @Schema(description = "承载能力")
    @ExcelProperty("承载能力")
    private String bearingCapacity;

    @Schema(description = "服务范围")
    @ExcelProperty("服务范围")
    private String serviceScope;

    @Schema(description = "服务人口")
    @ExcelProperty("服务人口")
    private String servicePopulation;

    @Schema(description = "资源配置")
    @ExcelProperty("资源配置")
    private String resourceAllocation;

    @Schema(description = "资金投入")
    @ExcelProperty("资金投入")
    private String capitalInvestment;

    @Schema(description = "预算安排")
    @ExcelProperty("预算安排")
    private String budgetArrangement;

    @Schema(description = "效益评估")
    @ExcelProperty("效益评估")
    private String benefitsAssessment;

    @Schema(description = "政策法规依据")
    @ExcelProperty("政策法规依据")
    private String policyRegulatoryBasis;

    @Schema(description = "行业标准规范")
    @ExcelProperty("行业标准规范")
    private String industryStandardSpecifications;

    @Schema(description = "安全隐患")
    @ExcelProperty("安全隐患")
    private String potentialSafetyHazard;

    @Schema(description = "风险评估")
    @ExcelProperty("风险评估")
    private String riskAssessment;

    @Schema(description = "应急处置预案")
    @ExcelProperty("应急处置预案")
    private String emergencyResponsePlan;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}