package cn.iocoder.yudao.module.studentmgmt.controller.admin.leavehandle.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 离校办理确认 Request VO")
@Data
public class LeaveHandleConfirmReqVO {

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25257")
    @NotNull(message = "学生 ID不能为空")
    private Long studentId;

    @Schema(description = "家长确认时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2022-02-07 01:01:01")
    @NotNull(message = "家长确认时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime parentConfirmTime;

}