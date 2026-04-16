package cn.iocoder.yudao.module.vehiclecharging.controller.admin.pilealarm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "汽车充电 - 充电桩告警 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PilealarmRespVO {

    @Schema(description = "主键ID（UUID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "3127")
    @ExcelProperty("主键ID（UUID）")
    private String id;

    @Schema(description = "告警编码（唯一）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("告警编码（唯一）")
    private String alarmCode;

    @Schema(description = "充电桩ID（关联charging_pile）", requiredMode = Schema.RequiredMode.REQUIRED, example = "21442")
    @ExcelProperty("充电桩ID（关联charging_pile）")
    private String pileId;

    @Schema(description = "所属场站ID（关联charging_station）", requiredMode = Schema.RequiredMode.REQUIRED, example = "32279")
    @ExcelProperty("所属场站ID（关联charging_station）")
    private String stationId;

    @Schema(description = "故障类型：硬件/软件/网络/计费 字典类型：pilealarm_fault_type", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("故障类型：硬件/软件/网络/计费 字典类型：pilealarm_fault_type")
    private String faultType;

    @Schema(description = "故障描述")
    @ExcelProperty("故障描述")
    private String faultDesc;

    @Schema(description = "告警等级：一级/二级/三级 字典类型：pilealarm_alarm_level", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("告警等级：一级/二级/三级 字典类型：pilealarm_alarm_level")
    private String alarmLevel;

    @Schema(description = "告警时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("告警时间")
    private LocalDateTime alarmTime;

    @Schema(description = "处理人ID（关联system_user）", example = "5887")
    @ExcelProperty("处理人ID（关联system_user）")
    private Long handlerId;

    @Schema(description = "处理时长（小时）")
    @ExcelProperty("处理时长（小时）")
    private BigDecimal handleHour;

    @Schema(description = "处理结果")
    @ExcelProperty("处理结果")
    private String handleResult;

    @Schema(description = "告警状态：未确认/已确认/处理中 字典类型：pilealarm_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("告警状态：未确认/已确认/处理中 字典类型：pilealarm_status")
    private String status;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "备用字段3")
    @ExcelProperty("备用字段3")
    private String reserve3;

    @Schema(description = "创建人（关联system_user）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人（关联system_user）")
    private Long createBy;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人")
    @ExcelProperty("更新人")
    private Long updateBy;

}