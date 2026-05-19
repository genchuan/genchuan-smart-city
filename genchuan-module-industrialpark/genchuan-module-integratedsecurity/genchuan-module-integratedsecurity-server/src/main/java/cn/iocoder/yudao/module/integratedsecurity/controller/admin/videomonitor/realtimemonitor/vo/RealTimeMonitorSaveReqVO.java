package cn.iocoder.yudao.module.integratedsecurity.controller.admin.videomonitor.realtimemonitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 实时监控新增/修改 Request VO")
@Data
public class RealTimeMonitorSaveReqVO {

    @Schema(description = "主键ID", example = "4799")
    private Long id;

    @Schema(description = "摄像头ID 关联camera_mgmt表id", requiredMode = Schema.RequiredMode.REQUIRED, example = "19931")
    @NotNull(message = "摄像头ID 关联camera_mgmt表id不能为空")
    private Long cameraId;

    @Schema(description = "摄像头名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "大门摄像头")
    @NotEmpty(message = "摄像头名称不能为空")
    private String cameraName;

    @Schema(description = "安装区域", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "安装区域不能为空")
    private String area;

    @Schema(description = "运行状态：正常/异常 关联字典：real_time_monitor_run_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "运行状态：正常/异常 关联字典：real_time_monitor_run_status不能为空")
    private String runStatus;

    @Schema(description = "告警状态：无告警/告警中 关联字典：real_time_monitor_alarm_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "告警状态：无告警/告警中 关联字典：real_time_monitor_alarm_status不能为空")
    private String alarmStatus;

    @Schema(description = "监控画面地址", example = "https://www.iocoder.cn")
    private String imgUrl;

    @Schema(description = "操作人账号 关联芋道用户表")
    private String handleUser;

    @Schema(description = "处置结果")
    private String handleResult;

    @Schema(description = "截图记录")
    private String snapImg;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}