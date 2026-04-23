package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 用户申诉审核 Request VO")
@Data
public class UserAppealAuditReqVO {

    @Schema(description = "申诉 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "申诉 ID 不能为空")
    private Long id;

    @Schema(description = "是否通过", requiredMode = Schema.RequiredMode.REQUIRED, example = "true")
    @NotNull(message = "审核结果不能为空")
    private Boolean approved;

    @Schema(description = "审核备注")
    private String auditRemark;

    @Schema(description = "驳回理由（驳回时必填）")
    private String rejectReason;

    @AssertTrue(message = "驳回时必须填写驳回理由")
    public boolean isRejectReasonValid() {
        return Boolean.TRUE.equals(approved) || (rejectReason != null && !rejectReason.isEmpty());
    }

}
