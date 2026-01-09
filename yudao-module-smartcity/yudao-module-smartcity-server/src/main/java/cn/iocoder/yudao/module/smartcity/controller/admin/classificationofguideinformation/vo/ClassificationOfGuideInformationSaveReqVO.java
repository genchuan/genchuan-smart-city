package cn.iocoder.yudao.module.smartcity.controller.admin.classificationofguideinformation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 指南信息分类新增/修改 Request VO")
@Data
public class ClassificationOfGuideInformationSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "14005")
    private Long id;

    @Schema(description = "适用领域")
    private String applications;

    @Schema(description = "应用场景")
    private String applicationScenarios;

    @Schema(description = "受众群体")
    private String targetAudience;

    @Schema(description = "指南性质")
    private String natureOfTheGuide;

}