package cn.iocoder.yudao.module.datacenter.controller.admin.staffworkstatus.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 人员作业状态 Response VO")
@Data
@ExcelIgnoreUnannotated
public class StaffWorkStatusRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "监控ID")
    @ExcelProperty("监控ID")
    private String monitorId;

    @Schema(description = "人员ID")
    @ExcelProperty("人员ID")
    private String staffId;

    @Schema(description = "人员姓名")
    @ExcelProperty("人员姓名")
    private String staffName;

    @Schema(description = "作业状态")
    @ExcelProperty("作业状态")
    private String workStatus;

    @Schema(description = "当前任务ID")
    @ExcelProperty("当前任务ID")
    private String currentTaskId;

    @Schema(description = "任务名称")
    @ExcelProperty("任务名称")
    private String taskName;

    @Schema(description = "GPS坐标")
    @ExcelProperty("GPS坐标")
    private String gpsCoordinates;

    @Schema(description = "定位时间")
    @ExcelProperty("定位时间")
    private LocalDateTime locationTime;

    @Schema(description = "已完成任务数")
    @ExcelProperty("已完成任务数")
    private Integer completedTaskCount;

    @Schema(description = "剩余任务数")
    @ExcelProperty("剩余任务数")
    private Integer remainingTaskCount;

    @Schema(description = "异常状态")
    @ExcelProperty("异常状态")
    private String abnormalStatus;

    @Schema(description = "异常开始时间")
    @ExcelProperty("异常开始时间")
    private LocalDateTime abnormalStartTime;

    @Schema(description = "所属区域ID")
    @ExcelProperty("所属区域ID")
    private String areaId;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}