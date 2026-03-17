package cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.question.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 题目 Response VO")
@Data
@ExcelIgnoreUnannotated
public class QuestionRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11401")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "题目ID（UUID）", example = "7853")
    @ExcelProperty("题目ID（UUID）")
    private String questionId;

    @Schema(description = "问卷ID（关联survey_questionnaire.questionnaire_id）", example = "19300")
    @ExcelProperty("问卷ID（关联survey_questionnaire.questionnaire_id）")
    private String questionnaireId;

    @Schema(description = "题目名称", example = "王五")
    @ExcelProperty("题目名称")
    private String title;

    @Schema(description = "题目类型：单选/多选/打分题", example = "1")
    @ExcelProperty("题目类型：单选/多选/打分题")
    private String questionType;

    @Schema(description = "分值设置，仅打分题")
    @ExcelProperty("分值设置，仅打分题")
    private String scoreRange;

    @Schema(description = "题目分值")
    @ExcelProperty("题目分值")
    private Integer score;

    @Schema(description = "创建人，关联sys_user.user_id")
    @ExcelProperty("创建人，关联sys_user.user_id")
    private Integer createBy;

    @Schema(description = "更新人，关联sys_user.user_id")
    @ExcelProperty("更新人，关联sys_user.user_id")
    private Integer updateBy;

    @Schema(description = "排序序号")
    @ExcelProperty("排序序号")
    private Integer sortNo;

    @Schema(description = "创建时间（业务字段）")
    @ExcelProperty("创建时间（业务字段）")
    private LocalDateTime bizCreateTime;

    @Schema(description = "更新时间（业务字段）")
    @ExcelProperty("更新时间（业务字段）")
    private LocalDateTime bizUpdateTime;

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
