package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 在停状态统计 Request VO")
@Data
public class InParkStatusChartReqVO {

    @Schema(description = "场站ID", example = "1")
    private Long stationId;

}