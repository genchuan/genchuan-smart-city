package cn.iocoder.yudao.module.inspectop.controller.admin.inspectplan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 巡检计划状态更新 Request VO")
@Data
public class InspectPlanStatusReqVO {

    @Schema(description = "计划ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "计划ID不能为空")
    private Long id;
}