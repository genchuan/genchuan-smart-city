package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 用户申诉批量审核 Request VO")
@Data
public class UserAppealBatchAuditReqVO {

    public static final String AUDIT_RESULT_PASS = "通过";
    public static final String AUDIT_RESULT_REJECT = "驳回";

    @Schema(description = "用户申诉记录 ID 数组,仅支持待审核状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "申诉 ID 列表不能为空")
    private List<Long> ids;

    @Schema(description = "审核结果,通过/驳回", requiredMode = Schema.RequiredMode.REQUIRED, example = "通过")
    @NotBlank(message = "审核结果不能为空")
    @Pattern(regexp = "通过|驳回", message = "审核结果必须是:通过/驳回")
    private String auditResult;

    @Schema(description = "审核备注")
    private String auditRemark;

    @Schema(description = "驳回理由(驳回时必填)")
    private String rejectReason;

    @AssertTrue(message = "驳回时必须填写驳回理由")
    public boolean isRejectReasonValid() {
        return !AUDIT_RESULT_REJECT.equals(auditResult) || (rejectReason != null && !rejectReason.isEmpty());
    }

}
