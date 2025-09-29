package cn.iocoder.yudao.module.datacenter.controller.admin.staffworkstatus.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 人员作业状态分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StaffWorkStatusPageReqVO extends PageParam {

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
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] locationTime;

    @Schema(description = "已完成任务数")
    private Integer completedTaskCount;

    @Schema(description = "剩余任务数")
    private Integer remainingTaskCount;

    @Schema(description = "异常状态")
    private String abnormalStatus;

    @Schema(description = "异常开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] abnormalStartTime;

    @Schema(description = "所属区域ID")
    private String areaId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}