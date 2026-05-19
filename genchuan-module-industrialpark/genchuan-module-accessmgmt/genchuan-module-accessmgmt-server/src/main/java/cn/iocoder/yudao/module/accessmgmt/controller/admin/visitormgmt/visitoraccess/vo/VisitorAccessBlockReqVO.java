package cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitoraccess.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 禁行 Request VO")
@Data
public class VisitorAccessBlockReqVO {

    @Schema(description = "访客通行ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "访客通行ID不能为空")
    private Long id;

    @Schema(description = "禁行原因", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "禁行原因不能为空")
    private String blockReason;

}
