package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 无牌入场统计 Request VO")
@Data
public class UnplateEnterChartReqVO {

    @Schema(description = "统计开始时间，格式时间戳", requiredMode = Schema.RequiredMode.REQUIRED, example = "1774147200")
    @NotBlank(message = "统计开始时间不能为空")
    private String startTime;

    @Schema(description = "统计结束时间，格式时间戳", requiredMode = Schema.RequiredMode.REQUIRED, example = "1775011200")
    @NotBlank(message = "统计结束时间不能为空")
    private String endTime;

    @Schema(description = "场站ID", example = "1")
    private Long stationId;

}