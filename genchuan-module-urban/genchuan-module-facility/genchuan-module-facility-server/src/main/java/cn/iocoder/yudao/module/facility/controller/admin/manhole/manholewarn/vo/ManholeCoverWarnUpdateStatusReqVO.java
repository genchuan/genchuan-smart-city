package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "窨井盖预警状态更新 Request VO")
@Data
public class ManholeCoverWarnUpdateStatusReqVO {

    @Schema(description = "预警状态 1-处理中，2-已解决，3-已忽略", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "预警状态不能为空")
    private Integer warnStatus;

    @Schema(description = "处置备注")
    private String handleRemark;

    @Schema(description = "租户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "租户ID不能为空")
    private String tenantId;

    @Schema(description = "操作人ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "操作人ID不能为空")
    private String operateUserId;
}
