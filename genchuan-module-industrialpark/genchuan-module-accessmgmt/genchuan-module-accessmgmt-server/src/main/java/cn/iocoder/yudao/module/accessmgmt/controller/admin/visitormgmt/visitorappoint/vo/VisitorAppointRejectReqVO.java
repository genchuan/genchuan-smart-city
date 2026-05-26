package cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitorappoint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 访客预约驳回 Request VO")
@Data
public class VisitorAppointRejectReqVO {

    @Schema(description = "访客预约ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "访客预约ID不能为空")
    private Long id;

    @Schema(description = "驳回原因", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "驳回原因不能为空")
    private String rejectReason;

}
