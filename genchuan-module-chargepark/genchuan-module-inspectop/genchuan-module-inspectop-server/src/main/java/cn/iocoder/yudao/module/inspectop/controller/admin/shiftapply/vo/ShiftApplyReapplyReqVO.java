package cn.iocoder.yudao.module.inspectop.controller.admin.shiftapply.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Schema(description = "巡查巡检 - 重新申请换班 Request VO")
@Data
public class ShiftApplyReapplyReqVO {

    @Schema(description = "原申请ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "原申请ID不能为空")
    private Long id;

    @Schema(description = "新申请备注")
    private String newRemark;
}