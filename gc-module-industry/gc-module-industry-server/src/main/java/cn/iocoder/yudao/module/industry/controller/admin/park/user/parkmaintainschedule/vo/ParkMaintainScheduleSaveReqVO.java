package cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmaintainschedule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 运维排班新增/修改 Request VO")
@Data
public class ParkMaintainScheduleSaveReqVO {

    @Schema(description = "[主键ID] 排班记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "12829")
    private Long id;

    @Schema(description = "[运维人员ID] 关联 park_maintain_user.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "25396")
    @NotNull(message = "[运维人员ID] 关联 park_maintain_user.id不能为空")
    private Long maintainUserId;

    @Schema(description = "[排班日期] 排班日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[排班日期] 排班日期不能为空")
    private LocalDate scheduleDate;

    @Schema(description = "[班次类型] 早班 / 中班 / 晚班 / 全天", example = "1")
    private String shiftType;

    @Schema(description = "[上班时间] 上班时间")
    private LocalTime startTime;

    @Schema(description = "[下班时间] 下班时间")
    private LocalTime endTime;

    @Schema(description = "[状态] 排班状态：正常 / 调班 / 取消", example = "1")
    private String status;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

    @Schema(description = "[备注] 排班相关备注说明", example = "随便")
    private String remark;

}
