package cn.iocoder.yudao.module.park.controller.admin.park.user.maintainschedule.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Schema(description = "管理后台 - 运维排班 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MaintainScheduleRespVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "13940")
    @ExcelProperty("[主键ID]")
    private Long id;

    @Schema(description = "[运维人员ID] 关联park_maintain_user.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "23848")
    @ExcelProperty("[运维人员ID] 关联park_maintain_user.id")
    private Long maintainUserId;

    @Schema(description = "[部门ID] 关联park_dept.id", example = "8753")
    @ExcelProperty("[部门ID] 关联park_dept.id")
    private Long deptId;

    @Schema(description = "[排班日期]")
    @ExcelProperty("[排班日期]")
    private LocalDate scheduleDate;

    @Schema(description = "[班次类型] 如:早班/中班/晚班/夜班", example = "2")
    @ExcelProperty("[班次类型] 如:早班/中班/晚班/夜班")
    private String shiftType;

    @Schema(description = "[上班时间]")
    @ExcelProperty("[上班时间]")
    private LocalTime startTime;

    @Schema(description = "[下班时间]")
    @ExcelProperty("[下班时间]")
    private LocalTime endTime;

    @Schema(description = "[状态] 如:正常/调班/取消", example = "2")
    @ExcelProperty("[状态] 如:正常/调班/取消")
    private String status;

    @Schema(description = "[调整原因] 可为NULL", example = "不香")
    @ExcelProperty("[调整原因] 可为NULL")
    private String adjustReason;

    @Schema(description = "[排班人ID] 关联park_user.id")
    @ExcelProperty("[排班人ID] 关联park_user.id")
    private Long createBy;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注]", example = "随便")
    @ExcelProperty("[备注]")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    @ExcelProperty("[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    @ExcelProperty("[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    @ExcelProperty("[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    @ExcelProperty("[通用扩展字段4]")
    private String extCommon4;

}
