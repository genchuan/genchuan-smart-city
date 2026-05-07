package cn.iocoder.yudao.module.vehiclecharging.controller.admin.pilealarm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import cn.idev.excel.annotation.*;

import java.time.LocalDateTime;

@Data
@Schema(description = "充电桩告警分页 Response VO")
@ExcelIgnoreUnannotated
public class NewPileAlarmRespVO {

    @Schema(description = "主键")
    @ExcelProperty("主键")
    private String id;

    @Schema(description = "告警编号")
    @ExcelProperty("告警编号")
    private String alarmCode;

    @Schema(description = "充电桩编号")
    @ExcelProperty("充电桩编号")
    private String pileCode;

    @Schema(description = "充电桩名称")
    @ExcelProperty("充电桩名称")
    private String pileName;

    @Schema(description = "场站ID")
    @ExcelProperty("场站ID")
    private String stationId;

    @Schema(description = "场站名称")
    @ExcelProperty("场站名称")
    private String stationName;

    @Schema(description = "故障类型")
    @ExcelProperty("故障类型")
    private String faultType;

    @Schema(description = "告警等级")
    @ExcelProperty("告警等级")
    private String alarmLevel;

    @Schema(description = "告警时间")
    @ExcelProperty("告警时间")
    private LocalDateTime alarmTime;

    @Schema(description = "处理人ID")
    @ExcelProperty("处理人ID")
    private Long handleUser;

    @Schema(description = "处理人姓名")
    @ExcelProperty("处理人姓名")
    private String handleUserName;

    @Schema(description = "告警状态")
    @ExcelProperty("告警状态")
    private String alarmStatus;

    @Schema(description = "处置措施")
    @ExcelProperty("处置措施")
    private String disposeMeasure;

    @Schema(description = "处置时间")
    @ExcelProperty("处置时间")
    private LocalDateTime disposeTime;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;
}