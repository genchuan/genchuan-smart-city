package cn.iocoder.yudao.module.studentmgmt.controller.admin.behaviormgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 各班级请假次数 / 考勤异常人数统计 Request VO")
@Data
public class BehaviorMgmtAttendanceCountReqVO {

    @Schema(description = "时间范围", example = "时间范围参数需要符合yyyy-MM-dd HH:mm:ss格式")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] timeRange;

    @Schema(description = "年级，支持年级维度筛选。")
    private String grade;

}