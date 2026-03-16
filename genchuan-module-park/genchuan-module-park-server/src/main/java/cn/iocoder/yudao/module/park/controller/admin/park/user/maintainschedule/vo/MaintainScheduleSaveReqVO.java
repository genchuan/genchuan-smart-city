package cn.iocoder.yudao.module.park.controller.admin.park.user.maintainschedule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Schema(description = "管理后台 - 运维排班新增/修改 Request VO")
@Data
public class MaintainScheduleSaveReqVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "13940")
    private Long id;

    @Schema(description = "[运维人员ID] 关联park_maintain_user.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "23848")
    @NotNull(message = "[运维人员ID] 关联park_maintain_user.id不能为空")
    private Long maintainUserId;

    @Schema(description = "[部门ID] 关联park_dept.id", example = "8753")
    private Long deptId;

    @Schema(description = "[排班日期]")
    private LocalDate scheduleDate;

    @Schema(description = "[班次类型] 如:早班/中班/晚班/夜班", example = "2")
    private String shiftType;

    @Schema(description = "[上班时间]")
    private LocalTime startTime;

    @Schema(description = "[下班时间]")
    private LocalTime endTime;

    @Schema(description = "[状态] 如:正常/调班/取消", example = "2")
    private String status;

    @Schema(description = "[调整原因] 可为NULL", example = "不香")
    private String adjustReason;

    @Schema(description = "[排班人ID] 关联park_user.id")
    private Long createBy;

    @Schema(description = "[备注]", example = "随便")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}
