package cn.iocoder.yudao.module.vehiclecharging.controller.admin.modulealarm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Schema(description = "汽车充电 - 模块告警记录修复 Request VO")
@Data
public class ModuleAlarmRepairReqVO {

    @Schema(description = "告警记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "告警记录ID不能为空")
    private Long id;

    @Schema(description = "修复凭证，文件访问地址", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://xxx.com/repair/20250328/xxx.png")
    @NotBlank(message = "修复凭证不能为空")
    private String repairVoucher;

}
