package cn.iocoder.yudao.module.vehiclecharging.controller.admin.modulealarm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Schema(description = "管理后台 - 模块告警记录备注 Request VO")
@Data
public class ModuleAlarmRemarkReqVO {

    @Schema(description = "告警记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "告警记录ID不能为空")
    private Long id;

    @Schema(description = "备注内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "已调整数据库连接池参数，后续观察服务运行状态")
    @NotBlank(message = "备注内容不能为空")
    private String remark;

}
