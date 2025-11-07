package cn.iocoder.yudao.module.smartcity.controller.admin.classificationofpoliciesandregulations.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 政策法规分类新增/修改 Request VO")
@Data
public class ClassificationOfPoliciesAndRegulationsSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "4595")
    private Long id;

    @Schema(description = "法规类别")
    private String regulatoryCategory;

    @Schema(description = "所属领域")
    private String isArea;

    @Schema(description = "主题")
    private String theme;

    @Schema(description = "适用对象")
    private String applicableObjects;

    @Schema(description = "效力层级")
    private String levelOfEffectiveness;

    @Schema(description = "发布部门")
    private String departmentOfPublication;

    @Schema(description = "实施时间")
    private String implementationTime;

    @Schema(description = "废止时间 ")
    private String revocatoryDate;

}