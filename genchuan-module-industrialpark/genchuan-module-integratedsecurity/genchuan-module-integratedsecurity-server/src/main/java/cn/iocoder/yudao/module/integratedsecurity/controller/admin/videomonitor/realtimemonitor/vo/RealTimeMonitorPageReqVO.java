package cn.iocoder.yudao.module.integratedsecurity.controller.admin.videomonitor.realtimemonitor.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 实时监控分页 Request VO")
@Data
public class RealTimeMonitorPageReqVO extends PageParam {

    @Schema(description = "摄像头ID 关联camera_mgmt表id", example = "19931")
    private Long cameraId;

    @Schema(description = "摄像头名称，支持模糊查询", example = "大门摄像头")
    private String cameraName;

    @Schema(description = "安装区域，支持模糊查询")
    private String area;

    @Schema(description = "运行状态：正常/异常 关联字典：real_time_monitor_run_status", example = "1")
    private String runStatus;

    @Schema(description = "告警状态：无告警/告警中 关联字典：real_time_monitor_alarm_status", example = "1")
    private String alarmStatus;

}