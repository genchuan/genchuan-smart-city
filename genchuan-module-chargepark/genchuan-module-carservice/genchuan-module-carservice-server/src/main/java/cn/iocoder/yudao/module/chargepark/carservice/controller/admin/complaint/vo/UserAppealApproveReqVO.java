package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 用户申诉通过 Request VO")
@Data
public class UserAppealApproveReqVO {

    @Schema(description = "申诉 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "申诉 ID 不能为空")
    private Long id;

    @Schema(description = "审核备注")
    private String auditRemark;

}
