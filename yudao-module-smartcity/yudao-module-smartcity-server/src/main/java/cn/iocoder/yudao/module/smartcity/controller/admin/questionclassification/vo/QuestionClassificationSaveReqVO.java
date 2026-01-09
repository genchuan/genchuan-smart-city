package cn.iocoder.yudao.module.smartcity.controller.admin.questionclassification.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 问题录入新增/修改 Request VO")
@Data
public class QuestionClassificationSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "10833")
    private Long id;

    @Schema(description = "所属领域")
    private String isArea;

    @Schema(description = "问题类型", example = "1")
    private String questionType;

    @Schema(description = "紧急程度")
    private String urgency;

    @Schema(description = "涉及主体")
    private String involvingTheSubject;

}