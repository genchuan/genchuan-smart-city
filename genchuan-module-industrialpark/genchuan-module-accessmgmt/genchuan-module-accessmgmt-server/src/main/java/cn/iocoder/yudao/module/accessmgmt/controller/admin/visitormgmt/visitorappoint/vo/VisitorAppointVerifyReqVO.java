package cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitorappoint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "管理后台 - 到访验证 Request VO")
@Data
public class VisitorAppointVerifyReqVO {

    @Schema(description = "通行凭证", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "通行凭证不能为空")
    private String ticket;

}
