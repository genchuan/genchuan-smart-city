package cn.iocoder.yudao.module.inspectop.controller.admin.shiftapply.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Schema(description = "巡查巡检 - 通过换班申请 Request VO")
@Data
public class ShiftApplyApproveReqVO {

    @Schema(description = "申请ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "申请ID不能为空")
    private Long id;

    @Schema(description = "审核备注")
    private String auditRemark;
}