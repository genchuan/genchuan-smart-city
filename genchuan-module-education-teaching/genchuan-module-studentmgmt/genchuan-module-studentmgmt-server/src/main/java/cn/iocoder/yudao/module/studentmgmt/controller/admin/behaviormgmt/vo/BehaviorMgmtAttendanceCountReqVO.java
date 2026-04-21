package cn.iocoder.yudao.module.studentmgmt.controller.admin.behaviormgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 各班级请假次数 / 考勤异常人数统计 Request VO")
@Data
public class BehaviorMgmtAttendanceCountReqVO {

    @Schema(description = "统计时间范围，开始时间和结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计时间范围，开始时间和结束时间")
    private LocalDateTime[] timeRange;

    @Schema(description = "年级，支持年级维度筛选。")
    private String grade;

}