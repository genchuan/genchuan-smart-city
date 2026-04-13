package cn.iocoder.yudao.module.studentmgmt.controller.admin.behaviormgmt.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 行为管理分页 Request VO")
@Data
public class BehaviorMgmtPageReqVO extends PageParam {

    @Schema(description = "学生 ID", example = "4497")
    private Long studentId;

    @Schema(description = "请假类型：事假/病假/其他", example = "2")
    private String leaveType;

    @Schema(description = "请假开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] startTime;

    @Schema(description = "请假结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] endTime;

    @Schema(description = "请假原因", example = "不喜欢")
    private String leaveReason;

    @Schema(description = "审批级别：班主任/辅导员")
    private String auditLevel;

    @Schema(description = "审批人")
    private String auditUser;

    @Schema(description = "审批时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] auditTime;

    @Schema(description = "考勤同步状态：未同步/已同步")
    private String attendanceSync;

    @Schema(description = "状态：待审批/已通过/已驳回", example = "1")
    private String status;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}