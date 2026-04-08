package cn.iocoder.yudao.module.vehiclecharging.controller.admin.modulealarm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 模块告警状态数量统计 Request VO")
@Data
public class ModuleAlarmCountReqVO {

    @Schema(description = "统计开始时间", example = "2025-03-01 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @Schema(description = "统计结束时间", example = "2025-03-31 23:59:59")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

}
