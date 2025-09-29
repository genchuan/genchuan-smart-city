package cn.iocoder.yudao.module.datacenter.controller.admin.staffworkstatus.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 人员作业状态新增/修改 Request VO")
@Data
public class StaffWorkStatusSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "监控ID")
    private String monitorId;

    @Schema(description = "人员ID")
    private String staffId;

    @Schema(description = "人员姓名")
    private String staffName;

    @Schema(description = "作业状态")
    private String workStatus;

    @Schema(description = "当前任务ID")
    private String currentTaskId;

    @Schema(description = "任务名称")
    private String taskName;

    @Schema(description = "GPS坐标")
    private String gpsCoordinates;

    @Schema(description = "定位时间")
    private LocalDateTime locationTime;

    @Schema(description = "已完成任务数")
    private Integer completedTaskCount;

    @Schema(description = "剩余任务数")
    private Integer remainingTaskCount;

    @Schema(description = "异常状态")
    private String abnormalStatus;

    @Schema(description = "异常开始时间")
    private LocalDateTime abnormalStartTime;

    @Schema(description = "所属区域ID")
    private String areaId;

}