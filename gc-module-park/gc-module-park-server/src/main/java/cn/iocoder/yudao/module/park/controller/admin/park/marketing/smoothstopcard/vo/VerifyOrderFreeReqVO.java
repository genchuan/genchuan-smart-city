package cn.iocoder.yudao.module.park.controller.admin.park.marketing.smoothstopcard.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 校验生效 VO")
@Data
public class VerifyOrderFreeReqVO {
    @Schema(description = "[车牌号]", requiredMode = Schema.RequiredMode.REQUIRED, example = "京A12345")
    @NotEmpty(message = "[车牌号]不能为空")
    private String carNumber;

    @Schema(description = "[车场ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "[车场ID]不能为空")
    private Long parkLotId;
}
