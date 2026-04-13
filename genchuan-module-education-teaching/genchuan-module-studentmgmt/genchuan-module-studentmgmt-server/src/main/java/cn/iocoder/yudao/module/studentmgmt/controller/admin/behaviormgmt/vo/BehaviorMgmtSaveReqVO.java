package cn.iocoder.yudao.module.studentmgmt.controller.admin.behaviormgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 行为管理新增/修改 Request VO")
@Data
public class BehaviorMgmtSaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3836")
    private Long id;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4497")
    @NotNull(message = "学生 ID不能为空")
    private Long studentId;

    @Schema(description = "请假类型：事假/病假/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "请假类型：事假/病假/其他不能为空")
    private String leaveType;

    @Schema(description = "请假开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "请假开始时间不能为空")
    private LocalDateTime startTime;

    @Schema(description = "请假结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "请假结束时间不能为空")
    private LocalDateTime endTime;

    @Schema(description = "请假原因", example = "不喜欢")
    private String leaveReason;

    @Schema(description = "审批级别：班主任/辅导员")
    private String auditLevel;

    @Schema(description = "审批人")
    private String auditUser;

    @Schema(description = "审批时间")
    private LocalDateTime auditTime;

    @Schema(description = "考勤同步状态：未同步/已同步")
    private String attendanceSync;

    @Schema(description = "状态：待审批/已通过/已驳回", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "状态：待审批/已通过/已驳回不能为空")
    private String status;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}