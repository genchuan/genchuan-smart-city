package cn.iocoder.yudao.module.smartcity.controller.admin.classificationofexperienceinformation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;

@Schema(description = "管理后台 - 经验信息分类新增/修改 Request VO")
@Data
public class ClassificationOfExperienceInformationSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "28173")
    private Long id;

    @Schema(description = "所属行业")
    private String sector;

    @Schema(description = "应用场景")
    private String applicationScenarios;

    @Schema(description = "经验性质")
    private String empiricalNature;

    @Schema(description = "适用对象")
    private String applicableObjects;

    @Schema(description = "来源渠道")
    private String sourceChannel;

}