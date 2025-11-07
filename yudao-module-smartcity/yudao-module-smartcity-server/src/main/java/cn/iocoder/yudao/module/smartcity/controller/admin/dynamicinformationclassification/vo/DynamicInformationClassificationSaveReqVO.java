package cn.iocoder.yudao.module.smartcity.controller.admin.dynamicinformationclassification.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 动态信息分类新增/修改 Request VO")
@Data
public class DynamicInformationClassificationSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "2221")
    private Long id;

    @Schema(description = "信息主题")
    private String messageSubject;

    @Schema(description = "行业领域")
    private String industrySector;

    @Schema(description = "信息来源")
    private String informationSources;

    @Schema(description = "紧急程度")
    private String urgency;

}