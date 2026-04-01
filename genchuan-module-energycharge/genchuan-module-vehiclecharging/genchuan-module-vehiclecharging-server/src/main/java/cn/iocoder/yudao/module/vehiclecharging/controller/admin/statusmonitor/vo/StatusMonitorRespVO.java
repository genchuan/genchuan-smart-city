package cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;

@Schema(description = "管理后台 - 实时监测 Response VO")
@Data
@ExcelIgnoreUnannotated
public class StatusMonitorRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3465")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "设备编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备编号")
    private String deviceCode;

    @Schema(description = "所属场站ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6947")
    @ExcelProperty("所属场站ID")
    private Long stationId;

    @Schema(description = "场站名称")
    @ExcelProperty("场站名称")
    private String stationName;

    @Schema(description = "所属车位ID", example = "11972")
    @ExcelProperty("所属车位ID")
    private Long lotId;

    @Schema(description = "车位编号")
    @ExcelProperty("车位编号")
    private String lotCode;

    @Schema(description = "设备类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("设备类型")
    private String deviceType;

    @Schema(description = "设备类型名称")
    @ExcelProperty("设备类型名称")
    private String deviceTypeName;

    @Schema(description = "电压 单位：V")
    @ExcelProperty("电压")
    private BigDecimal voltage;

    @Schema(description = "电流 单位：A")
    @ExcelProperty("电流")
    private BigDecimal current;

    @Schema(description = "功率 单位：kW")
    @ExcelProperty("功率")
    private BigDecimal power;

    @Schema(description = "告警等级")
    @ExcelProperty("告警等级")
    private String alarmLevel;

    @Schema(description = "告警等级名称")
    @ExcelProperty("告警等级名称")
    private String alarmLevelName;

    @Schema(description = "监测状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("监测状态")
    private String monitorStatus;

    @Schema(description = "监测状态名称")
    @ExcelProperty("监测状态名称")
    private String monitorStatusName;

    @Schema(description = "处置人员")
    @ExcelProperty("处置人员")
    private String disposeUser;

    @Schema(description = "处置人员名称")
    @ExcelProperty("处置人员名称")
    private String disposeUserName;

    @Schema(description = "处置措施")
    @ExcelProperty("处置措施")
    private String disposeMeasure;

    @Schema(description = "处置时间")
    @ExcelProperty("处置时间")
    private LocalDateTime disposeTime;

    @Schema(description = "监测时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("监测时间")
    private LocalDateTime monitorTime;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String creator;

    @Schema(description = "创建人名称")
    @ExcelProperty("创建人名称")
    private String createByName;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}
