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
    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25257")
    @NotNull(message = "ID不能为空")
    private Long id;

    @Schema(description = "家长确认时间，时间戳格式", requiredMode = Schema.RequiredMode.REQUIRED, example = "1745678900000")
    @NotNull(message = "家长确认时间不能为空")
    private LocalDateTime parentConfirmTime;

}