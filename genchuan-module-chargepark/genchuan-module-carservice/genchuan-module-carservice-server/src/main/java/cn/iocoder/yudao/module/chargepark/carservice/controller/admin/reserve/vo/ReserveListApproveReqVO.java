package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 预约通过 Request VO")
@Data
public class ReserveListApproveReqVO {

    @Schema(description = "预约 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "预约 ID 不能为空")
    private Long id;

    @Schema(description = "审核备注")
    private String auditRemark;

}
