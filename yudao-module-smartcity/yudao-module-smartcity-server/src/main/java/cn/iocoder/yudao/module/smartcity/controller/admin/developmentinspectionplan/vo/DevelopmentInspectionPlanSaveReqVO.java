package cn.iocoder.yudao.module.smartcity.controller.admin.developmentinspectionplan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 巡查计划管理新增/修改 Request VO")
@Data
public class DevelopmentInspectionPlanSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "24883")
    private Long id;

    @Schema(description = "巡查时间")
    private String patrolTime;

    @Schema(description = "区域")
    private String region;

    @Schema(description = "人员")
    private String staff;

}