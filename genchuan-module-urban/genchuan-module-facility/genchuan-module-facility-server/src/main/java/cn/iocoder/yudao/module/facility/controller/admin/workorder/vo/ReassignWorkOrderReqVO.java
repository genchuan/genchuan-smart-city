package cn.iocoder.yudao.module.facility.controller.admin.workorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 工单新增/修改 Request VO")
@Data
public class ReassignWorkOrderReqVO {
    @Schema(description = "[工单ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "工单id不能为空")
    private Long workOrderId;

    @Schema(description = "[重新指派运维人ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "[重新指派运维人ID]不能为空")
    private Long assignStaffId;

    @Schema(description = "[重新指派运维人名称]", requiredMode = Schema.RequiredMode.REQUIRED, example = "张德发")
    @NotEmpty(message = "[重新指派运维人名称]不能为空")
    private String assignStaffName;
}
