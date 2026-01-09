package cn.iocoder.yudao.module.smartcity.controller.admin.alarmhandlingcategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 报警处置类新增/修改 Request VO")
@Data
public class AlarmHandlingCategorySaveReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24176")
    private Long id;

    @Schema(description = "报警时间")
    private LocalDateTime time;

    @Schema(description = "报警来源")
    private String alarmSource;

    @Schema(description = "风险等级")
    private String riskLevel;

    @Schema(description = "报警描述", example = "你说的对")
    private String alarmDescription;

    @Schema(description = "涉及区域")
    private String involvingRegions;

    @Schema(description = "处置措施")
    private String disposalMeasures;

    @Schema(description = "处置结果")
    private String disposalResults;

    @Schema(description = "备注")
    private String notes;

}