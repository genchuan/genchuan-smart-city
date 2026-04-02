package cn.iocoder.yudao.module.vehiclecharging.controller.admin.ratesetting.vo.chart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
@Schema(description = "管理后台 - 费率档次数量统计 Request VO")
public class RateSettingGradeCountReqVO {

//    @NotBlank(message = "开始时间不能为空")
    @Schema(description = "开始时间，格式 yyyy-MM-dd", requiredMode = Schema.RequiredMode.REQUIRED, example = "2024-03-01")
    private String startTime;

//    @NotBlank(message = "结束时间不能为空")
    @Schema(description = "结束时间，格式 yyyy-MM-dd", requiredMode = Schema.RequiredMode.REQUIRED, example = "2027-03-31")
    private String endTime;

    @Schema(description = "场站ID", example = "1")
    private Long stationId;
}
