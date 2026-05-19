package cn.iocoder.yudao.module.integratedsecurity.controller.admin.videomonitor.realtimemonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.math.BigDecimal;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 实时监控 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RealTimeMonitorRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4799")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "摄像头ID 关联camera_mgmt表id", requiredMode = Schema.RequiredMode.REQUIRED, example = "19931")
    @ExcelProperty("摄像头ID 关联camera_mgmt表id")
    private Long cameraId;

    @Schema(description = "摄像头名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("摄像头名称")
    private String cameraName;

    @Schema(description = "安装区域", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("安装区域")
    private String area;

    @Schema(description = "运行状态：正常/异常 关联字典：real_time_monitor_run_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("运行状态：正常/异常 关联字典：real_time_monitor_run_status")
    private String runStatus;

    @Schema(description = "告警状态：无告警/告警中 关联字典：real_time_monitor_alarm_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("告警状态：无告警/告警中 关联字典：real_time_monitor_alarm_status")
    private String alarmStatus;

    @Schema(description = "监控画面地址", example = "https://www.iocoder.cn")
    @ExcelProperty("监控画面地址")
    private String imgUrl;

    @Schema(description = "经度", example = "118.675000")
    @ExcelProperty("经度")
    private BigDecimal lon;

    @Schema(description = "纬度", example = "24.896000")
    @ExcelProperty("纬度")
    private BigDecimal lat;

    @Schema(description = "告警内容")
    @ExcelProperty("告警内容")
    private String alarmContent;

    @Schema(description = "操作人账号 关联芋道用户表")
    @ExcelProperty("操作人账号 关联芋道用户表")
    private String handleUser;

    @Schema(description = "处置结果")
    @ExcelProperty("处置结果")
    private String handleResult;

    @Schema(description = "截图记录")
    @ExcelProperty("截图记录")
    private String snapImg;

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

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}