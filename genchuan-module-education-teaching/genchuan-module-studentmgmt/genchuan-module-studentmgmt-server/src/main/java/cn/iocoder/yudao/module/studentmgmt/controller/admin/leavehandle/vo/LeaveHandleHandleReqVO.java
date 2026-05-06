package cn.iocoder.yudao.module.studentmgmt.controller.admin.leavehandle.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 办理 Request VO")
@Data
public class LeaveHandleHandleReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25257")
    @NotNull(message = "ID不能为空")
    private Long id;
    @Schema(description = "办理人", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "办理人不能为空")
    private String handleUser;
    @Schema(description = "办理时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2022-02-07 01:01:01")
    @NotNull(message = "办理时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime handleTime;
    @Schema(description = "退宿时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2022-02-07 01:01:01")
    @NotNull(message = "退宿时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime checkoutTime;
    @Schema(description = "退宿状态（未退宿 / 已退宿），关联芋道字典表：leave_handle_checkout_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "left")
    @NotEmpty(message = "退宿状态（未退宿 / 已退宿），关联芋道字典表：leave_handle_checkout_status不能为空")
    private String checkoutStatus;

}