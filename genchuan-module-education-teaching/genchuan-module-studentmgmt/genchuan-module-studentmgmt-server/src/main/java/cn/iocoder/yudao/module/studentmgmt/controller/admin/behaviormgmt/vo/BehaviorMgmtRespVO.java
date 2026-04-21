package cn.iocoder.yudao.module.studentmgmt.controller.admin.behaviormgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 行为管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BehaviorMgmtRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3836")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4497")
    @ExcelProperty("学生 ID")
    private Long studentId;

    @Schema(description = "请假类型：事假/病假/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("请假类型：事假/病假/其他")
    private String leaveType;

    @Schema(description = "请假开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("请假开始时间")
    private LocalDateTime startTime;

    @Schema(description = "请假结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("请假结束时间")
    private LocalDateTime endTime;

    @Schema(description = "请假原因", example = "不喜欢")
    @ExcelProperty("请假原因")
    private String leaveReason;

    @Schema(description = "审批级别：班主任/辅导员")
    @ExcelProperty("审批级别：班主任/辅导员")
    private String auditLevel;

    @Schema(description = "审批人")
    @ExcelProperty("审批人")
    private String auditUser;

    @Schema(description = "审批时间")
    @ExcelProperty("审批时间")
    private LocalDateTime auditTime;

    @Schema(description = "考勤同步状态：未同步/已同步")
    @ExcelProperty("考勤同步状态：未同步/已同步")
    private String attendanceSync;

    @Schema(description = "状态：待审批/已通过/已驳回", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("状态：待审批/已通过/已驳回")
    private String status;

    @Schema(description = "备注", example = "随便")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段 1")
    @ExcelProperty("备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    @ExcelProperty("备用字段 2")
    private String reserve2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}