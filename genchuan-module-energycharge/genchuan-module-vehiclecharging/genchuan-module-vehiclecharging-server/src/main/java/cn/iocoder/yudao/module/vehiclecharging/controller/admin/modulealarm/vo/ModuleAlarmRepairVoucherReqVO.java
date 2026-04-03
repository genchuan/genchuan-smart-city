package cn.iocoder.yudao.module.vehiclecharging.controller.admin.modulealarm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Schema(description = "管理后台 - 模块告警记录修复凭证预览 Request VO")
@Data
public class ModuleAlarmRepairVoucherReqVO {

    @Schema(description = "告警记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "告警记录ID不能为空")
    private Long id;

}
