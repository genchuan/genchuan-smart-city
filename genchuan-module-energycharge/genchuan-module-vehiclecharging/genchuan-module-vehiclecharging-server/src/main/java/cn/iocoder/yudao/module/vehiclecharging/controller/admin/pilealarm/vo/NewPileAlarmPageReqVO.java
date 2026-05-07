package cn.iocoder.yudao.module.vehiclecharging.controller.admin.pilealarm.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Schema(description = "充电桩告警分页 Request VO")
public class NewPileAlarmPageReqVO extends PageParam {

    @Schema(description = "告警编号", example = "ALARM-20250328")
    private String alarmCode;

    @Schema(description = "充电桩编号", example = "PILE-001")
    private String pileCode;

    @Schema(description = "场站ID", example = "1001")
    private String stationId;

    @Schema(description = "场站名称", example = "场站")
    private String stationName;

    @Schema(description = "故障类型", example = "硬件故障")
    private String faultType;

    @Schema(description = "告警等级", example = "一级")
    private String alarmLevel;

    @Schema(description = "告警状态", example = "未派单")
    private String alarmStatus;

    @Schema(description = "告警开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime alarmTimeStart;

    @Schema(description = "告警结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime alarmTimeEnd;
}