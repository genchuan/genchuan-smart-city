package cn.iocoder.yudao.module.inspectop.controller.admin.handoverlog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Schema(description = "巡查巡检 - 交接日志确认 Request VO")
@Data
public class HandoverLogConfirmReqVO {

    @Schema(description = "日志ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "日志ID不能为空")
    private Long id;
}