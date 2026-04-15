package cn.iocoder.yudao.module.vehiclecharging.controller.admin.pilealarm.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "汽车充电 - 充电桩告警分页 Request VO")
@Data
public class PilealarmPageReqVO extends PageParam {

    @Schema(description = "告警编码（唯一）")
    private String alarmCode;

    @Schema(description = "充电桩ID（关联charging_pile）", example = "21442")
    private String pileId;

    @Schema(description = "所属场站ID（关联charging_station）", example = "32279")
    private String stationId;

    @Schema(description = "故障类型：硬件/软件/网络/计费 字典类型：pilealarm_fault_type", example = "1")
    private String faultType;

    @Schema(description = "故障描述")
    private String faultDesc;

    @Schema(description = "告警等级：一级/二级/三级 字典类型：pilealarm_alarm_level")
    private String alarmLevel;

    @Schema(description = "告警时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] alarmTime;

    @Schema(description = "处理人ID（关联system_user）", example = "5887")
    private Long handlerId;

    @Schema(description = "处理时长（小时）")
    private BigDecimal handleHour;

    @Schema(description = "处理结果")
    private String handleResult;

    @Schema(description = "告警状态：未确认/已确认/处理中 字典类型：pilealarm_status", example = "2")
    private String status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "备用字段3")
    private String reserve3;

    @Schema(description = "创建人（关联system_user）")
    private Long createBy;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人")
    private Long updateBy;

}