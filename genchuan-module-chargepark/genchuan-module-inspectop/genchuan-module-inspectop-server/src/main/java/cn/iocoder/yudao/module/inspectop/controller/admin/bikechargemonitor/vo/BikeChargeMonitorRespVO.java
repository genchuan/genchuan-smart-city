package cn.iocoder.yudao.module.inspectop.controller.admin.bikechargemonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "巡查巡检 - 两轮充电监测 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BikeChargeMonitorRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备ID")
    private Long deviceId;

    @Schema(description = "场站ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("场站ID")
    private Long stationId;

    @Schema(description = "场站名称")
    @ExcelProperty("场站名称")
    private String stationName;

    @Schema(description = "监测时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("监测时间")
    private LocalDateTime monitorTime;

    @Schema(description = "监测状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("监测状态")
    private String monitorStatus;

    @Schema(description = "告警状态")
    @ExcelProperty("告警状态")
    private String alarmStatus;

    @Schema(description = "告警时间")
    @ExcelProperty("告警时间")
    private LocalDateTime alarmTime;

    @Schema(description = "告警备注")
    @ExcelProperty("告警备注")
    private String alarmRemark;

    @Schema(description = "处理状态")
    @ExcelProperty("处理状态")
    private String processStatus;

    @Schema(description = "经度")
    @ExcelProperty("经度")
    private BigDecimal longitude;

    @Schema(description = "纬度")
    @ExcelProperty("纬度")
    private BigDecimal latitude;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}