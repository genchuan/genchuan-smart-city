package cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.oilmonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;

@Schema(description = "巡查巡检 - 油车占位监测忽略 Request VO")
@Data
@EqualsAndHashCode
@ToString
public class OilMonitorIgnoreReqVO {

    @Schema(description = "监测记录ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "监测记录ID不能为空")
    private Long id;

    @Schema(description = "忽略理由", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "忽略理由不能为空")
    private String ignoreReason;
}