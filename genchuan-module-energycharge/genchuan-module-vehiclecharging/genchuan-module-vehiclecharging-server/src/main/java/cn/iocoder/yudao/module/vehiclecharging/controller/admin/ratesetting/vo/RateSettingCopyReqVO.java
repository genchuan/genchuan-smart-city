package cn.iocoder.yudao.module.vehiclecharging.controller.admin.ratesetting.vo;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Schema(description = "汽车充电 - 费率方案复制 Request VO")
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

    @Schema(description = "生效开始时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2026-04-09 00:00:00")
    @NotNull(message = "生效开始时间不能为空")

    private LocalDateTime newStartTime;

    @Schema(description = "生效结束时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2026-06-09 00:11:11")
    @NotNull(message = "生效结束时间不能为空")

    private LocalDateTime newEndTime;

}
