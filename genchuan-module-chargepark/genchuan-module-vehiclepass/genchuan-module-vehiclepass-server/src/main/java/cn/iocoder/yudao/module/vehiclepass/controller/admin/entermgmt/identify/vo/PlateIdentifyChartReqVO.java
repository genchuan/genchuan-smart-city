package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.identify.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "管理后台 - 车牌识别统计 请求VO")
@Data
public class PlateIdentifyChartReqVO {

    @Schema(description = "统计开始时间(时间戳)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "统计开始时间不能为空")
    private String startTime;

    @Schema(description = "统计结束时间(时间戳)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "统计结束时间不能为空")
    private String endTime;

    @Schema(description = "场站ID")
    private Long stationId;
}