package cn.iocoder.yudao.module.inspectop.controller.admin.shiftapply.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "巡查巡检 - 驳回换班申请 Request VO")
@Data
public class ShiftApplyRejectReqVO {

    @Schema(description = "申请ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "申请ID不能为空")
    private Long id;

    @Schema(description = "驳回理由", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "驳回理由不能为空")
    @Size(min = 10, message = "驳回理由长度至少10个字")
    private String auditRemark;
}