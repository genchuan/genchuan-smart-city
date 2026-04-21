package cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.spacemonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "巡查巡检 - 车位状态监测新增/修改 Request VO")
@Data
public class SpaceMonitorSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "车位ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "车位ID不能为空")
    private Long spaceId;

    @Schema(description = "场站ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "场站ID不能为空")
    private Long stationId;

    @Schema(description = "监测时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "监测时间不能为空")
    private LocalDateTime monitorTime;

    @Schema(description = "监测状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "监测状态不能为空")
    private String monitorStatus;

    @Schema(description = "告警状态")
    private String alarmStatus;

    @Schema(description = "告警时间")
    private LocalDateTime alarmTime;

    @Schema(description = "告警备注")
    private String alarmRemark;

    @Schema(description = "处理状态")
    private String processStatus;

    @Schema(description = "经度")
    private BigDecimal longitude;

    @Schema(description = "纬度")
    private BigDecimal latitude;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}