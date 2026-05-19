package cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitoraccess.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 凭证核验 Request VO")
@Data
public class VisitorAccessCheckReqVO {

    @Schema(description = "预约ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "预约ID不能为空")
    private Long appointId;

    @Schema(description = "通行凭证", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "通行凭证不能为空")
    private String ticket;

}
