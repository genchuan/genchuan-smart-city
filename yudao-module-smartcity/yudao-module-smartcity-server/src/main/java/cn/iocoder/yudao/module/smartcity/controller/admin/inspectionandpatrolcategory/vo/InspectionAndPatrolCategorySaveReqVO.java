package cn.iocoder.yudao.module.smartcity.controller.admin.inspectionandpatrolcategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 巡查巡检类新增/修改 Request VO")
@Data
public class InspectionAndPatrolCategorySaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "32471")
    private Long id;

    @Schema(description = "编号")
    private String number;

    @Schema(description = "巡查巡检时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "巡查巡检时间不能为空")
    private LocalDateTime inspectionAndPatrolTime;

    @Schema(description = "巡查巡检人员")
    private String patrolPersonnel;

    @Schema(description = "巡查巡检区域")
    private String patrolInspectionArea;

    @Schema(description = "巡查巡检内容")
    private String inspectionAndPatrolContent;

    @Schema(description = "异常情况描述", example = "你说的对")
    private String abnormalSituationDescription;

    @Schema(description = "备注")
    private String notes;

}