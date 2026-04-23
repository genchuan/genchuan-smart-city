package cn.iocoder.yudao.module.studentmgmt.controller.admin.mentalmgmt.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 学生心理健康看板 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MentalMgmtChartRespVO {

    @Schema(description = "心理档案总数量")
    @ExcelProperty("心理档案总数量")
    private Integer totalCount;
    @Schema(description = "心理状态正常的学生数量")
    @ExcelProperty("心理状态正常的学生数量")
    private Integer normalCount;
    @Schema(description = "心理状态关注学生的数量")
    @ExcelProperty("心理状态关注学生的数量")
    private Integer focusCount;
    @Schema(description = "心理状态高危学生的数量")
    @ExcelProperty("心理状态高危学生的数量")
    private Integer highRiskCount;
    @Schema(description = "风险等级低学生的数量")
    @ExcelProperty("风险等级低学生的数量")
    private Integer lowRiskCount;
    @Schema(description = "风险等级中的学生的数量")
    @ExcelProperty("风险等级中的学生的数量")
    private Integer midRiskCount;
    @Schema(description = "风险等级高的学生的数量")
    @ExcelProperty("风险等级高的学生的数量")
    private Integer highRiskLevelCount;
    @Schema(description = "待评估状态的档案数量")
    @ExcelProperty("待评估状态的档案数量")
    private Integer waitEvaluateCount;
    @Schema(description = "咨询中状态的档案数量")
    @ExcelProperty("咨询中状态的档案数量")
    private Integer consultingCount;
    @Schema(description = "已干预状态的档案数量")
    @ExcelProperty("已干预状态的档案数量")
    private Integer intervenedCount;
    @Schema(description = "近 7 天新增心理档案数量")
    @ExcelProperty("近 7 天新增心理档案数量")
    private Integer recent7DayCount;
}
