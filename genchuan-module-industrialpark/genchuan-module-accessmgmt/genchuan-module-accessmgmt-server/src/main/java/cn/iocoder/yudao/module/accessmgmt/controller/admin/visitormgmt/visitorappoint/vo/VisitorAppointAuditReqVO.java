package cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitorappoint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 访客预约审核 Request VO")
@Data
public class VisitorAppointAuditReqVO {

    @Schema(description = "访客预约ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "访客预约ID不能为空")
    private Long id;

    @Schema(description = "审核结果", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "审核结果不能为空")
    private String checkResult;

}
