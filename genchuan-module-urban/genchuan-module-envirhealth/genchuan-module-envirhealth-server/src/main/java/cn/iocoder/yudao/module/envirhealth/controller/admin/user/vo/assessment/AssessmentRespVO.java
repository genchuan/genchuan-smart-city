package cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.assessment;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 考核 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssessmentRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18439")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "主键（UUID）", example = "9990")
    @ExcelProperty("主键（UUID）")
    private String assessmentId;

    @Schema(description = "关联sys_user.id", example = "1406")
    @ExcelProperty("关联sys_user.id")
    private String userId;

    @Schema(description = "关联sys_job_type.id", example = "12453")
    @ExcelProperty("关联sys_job_type.id")
    private String jobTypeId;

    @Schema(description = "关联sys_team.id", example = "16964")
    @ExcelProperty("关联sys_team.id")
    private String teamId;

    @Schema(description = "关联sys_area.area_code")
    @ExcelProperty("关联sys_area.area_code")
    private String areaCode;

    @Schema(description = "考核周期")
    @ExcelProperty("考核周期")
    private String cycle;

    @Schema(description = "考勤得分")
    @ExcelProperty("考勤得分")
    private BigDecimal attendanceScore;

    @Schema(description = "作业质量得分")
    @ExcelProperty("作业质量得分")
    private BigDecimal workQualityScore;

    @Schema(description = "问题处置得分")
    @ExcelProperty("问题处置得分")
    private BigDecimal problemSolvingScore;

    @Schema(description = "初始总分")
    @ExcelProperty("初始总分")
    private BigDecimal initialTotalScore;

    @Schema(description = "最终总分")
    @ExcelProperty("最终总分")
    private BigDecimal finalTotalScore;

    @Schema(description = "关联sys_assessment_grade.id", example = "7255")
    @ExcelProperty("关联sys_assessment_grade.id")
    private String assessmentGradeId;

    @Schema(description = "考核意见")
    @ExcelProperty("考核意见")
    private String reviewOpinion;

    @Schema(description = "关联sys_user.id")
    @ExcelProperty("关联sys_user.id")
    private String assessBy;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime reviewTime;

    @Schema(description = "佐证材料URL", example = "https://www.iocoder.cn")
    @ExcelProperty("佐证材料URL")
    private String proofUrl;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}