package cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.option.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 选项新增/修改 Request VO")
@Data
public class OptionSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23803")
    private Long id;

    @Schema(description = "选项ID（UUID）", example = "8759")
    private String optionId;

    @Schema(description = "题目ID（关联survey_question.question_id）", example = "30458")
    private String questionId;

    @Schema(description = "选项内容")
    private String optionContent;

    @Schema(description = "排序序号")
    private Integer sortNo;

    @Schema(description = "题目分值")
    private Integer score;

    @Schema(description = "创建人，关联sys_user.user_id")
    private Integer createBy;

    @Schema(description = "更新人，关联sys_user.user_id")
    private Integer updateBy;

    @Schema(description = "创建时间（业务字段）")
    private LocalDateTime bizCreateTime;

    @Schema(description = "更新时间（业务字段）")
    private LocalDateTime bizUpdateTime;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}