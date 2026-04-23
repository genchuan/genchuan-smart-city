package cn.iocoder.yudao.module.inspectop.controller.admin.inspectreport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "巡查巡检 - 巡检上报驳回 Request VO")
@Data
public class InspectReportRejectReqVO {

    @Schema(description = "上报ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "上报ID不能为空")
    private Long id;

    @Schema(description = "驳回理由", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "驳回理由不能为空")
    private String auditRemark;
}