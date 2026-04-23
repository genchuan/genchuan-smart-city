package cn.iocoder.yudao.module.inspectop.controller.admin.shiftapply.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;

@Schema(description = "巡查巡检 - 换班申请批量审核 Request VO")
@Data
public class ShiftApplyBatchAuditReqVO {

    @Schema(description = "申请ID数组", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "申请ID数组不能为空")
    @Size(min = 1, message = "至少需要一个申请ID")
    private List<Long> ids;

    @Schema(description = "审核结果(字典键值：2-通过/3-驳回)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "审核结果不能为空")
    private String auditResult;

    @Schema(description = "审核备注")
    private String auditRemark;
}