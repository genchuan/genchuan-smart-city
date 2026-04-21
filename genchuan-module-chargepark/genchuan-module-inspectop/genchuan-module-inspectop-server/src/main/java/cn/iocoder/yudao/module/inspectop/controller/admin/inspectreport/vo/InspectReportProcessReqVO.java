package cn.iocoder.yudao.module.inspectop.controller.admin.inspectreport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "巡查巡检 - 巡检上报执行 Request VO")
@Data
public class InspectReportProcessReqVO {

    @Schema(description = "上报ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "上报ID不能为空")
    private Long id;
}