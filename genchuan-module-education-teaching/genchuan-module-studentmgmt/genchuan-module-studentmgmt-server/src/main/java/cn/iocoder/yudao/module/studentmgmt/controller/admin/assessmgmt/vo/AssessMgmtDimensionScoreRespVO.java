package cn.iocoder.yudao.module.studentmgmt.controller.admin.assessmgmt.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 考评管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssessMgmtDimensionScoreRespVO {
    @Schema(description = "班级名称")
    @ExcelProperty("班级名称")
    private String className;
    @Schema(description = "教室卫生得分")
    @ExcelProperty("教室卫生得分")
    private BigDecimal classCleanScore;
    @Schema(description = "早操得分")
    @ExcelProperty("早操得分")
    private BigDecimal morningExerciseScore;
    @Schema(description = "文明班级得分")
    @ExcelProperty("文明班级得分")
    private BigDecimal civilClassScore;
    @Schema(description = "黑板报得分")
    @ExcelProperty("黑板报得分")
    private BigDecimal blackboardScore;
    @Schema(description = "总得分")
    @ExcelProperty("总得分")
    private BigDecimal totalScore;
}
