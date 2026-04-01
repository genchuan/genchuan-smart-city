package cn.iocoder.yudao.module.vehiclecharging.controller.admin.ratesetting.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "管理后台 - 费率方案复制 Request VO")
public class RateSettingCopyReqVO {

    @Schema(description = "原方案ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "原方案ID不能为空")
    private Long id;

    @Schema(description = "新方案编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "RATE-20250328")
    @NotBlank(message = "新方案编号不能为空")
    private String newRateCode;

    @Schema(description = "新方案名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "周末高峰费率")
    @NotBlank(message = "新方案名称不能为空")
    private String newRateName;

}
