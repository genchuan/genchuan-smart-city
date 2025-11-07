package cn.iocoder.yudao.module.smartcity.controller.admin.experienceinformationinput.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 经验信息录入新增/修改 Request VO")
@Data
public class ExperienceInformationInputSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "9500")
    private Long id;

    @Schema(description = "经验主题")
    private String experienceTheme;

    @Schema(description = "所属领域")
    private String isArea;

    @Schema(description = "经验提供方")
    private String experienceProvider;

    @Schema(description = "实施时间")
    private LocalDateTime implementationTime;

    @Schema(description = "实施地点")
    private String implementationLocation;

    @Schema(description = "详细步骤")
    private String detailSteps;

    @Schema(description = "取得成效")
    private String achieveResults;

    @Schema(description = "可借鉴要点")
    private String keyPointsForReference;

}