package cn.iocoder.yudao.module.inspectop.controller.admin.scheduleview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Schema(description = "巡查巡检 - 换班申请 Request VO")
@Data
public class ShiftApplyReqVO {

    @Schema(description = "排班ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "排班ID不能为空")
    private Long id;

    @Schema(description = "换班对象ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "换班对象ID不能为空")
    private Long targetUserId;

    @Schema(description = "新日期", requiredMode = Schema.RequiredMode.REQUIRED, example = "2026-01-01")
    @NotNull(message = "新日期不能为空")
    private String newDate;

    @Schema(description = "申请备注")
    private String applyRemark;
}