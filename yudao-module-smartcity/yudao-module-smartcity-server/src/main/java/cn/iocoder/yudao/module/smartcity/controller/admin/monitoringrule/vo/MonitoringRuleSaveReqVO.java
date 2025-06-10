package cn.iocoder.yudao.module.smartcity.controller.admin.monitoringrule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 监测项规则设置新增/修改 Request VO")
@Data
public class MonitoringRuleSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "27259")
    private Long id;

    @Schema(description = "水位超限预警")
    private String waterLevelOverlimitWarning;

    @Schema(description = "流量异常预警")
    private String flowAbnormalityWarning;

    @Schema(description = "水质超标预警")
    private String waterQualityExceedanceWarning;

}