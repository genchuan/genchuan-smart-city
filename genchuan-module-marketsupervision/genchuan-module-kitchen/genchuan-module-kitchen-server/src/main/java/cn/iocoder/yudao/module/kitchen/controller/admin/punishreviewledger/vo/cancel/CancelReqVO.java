package cn.iocoder.yudao.module.kitchen.controller.admin.punishreviewledger.vo.cancel;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CancelReqVO {
    @Schema(description = "台账ID", required = true)
    @NotNull(message = "台账ID 不为空")
    private Long id;

    @Schema(description = "撤销原因ID", required = true)
    @NotNull(message = "撤销原因ID 不为空")
    private Long cancelReasonId;
}
