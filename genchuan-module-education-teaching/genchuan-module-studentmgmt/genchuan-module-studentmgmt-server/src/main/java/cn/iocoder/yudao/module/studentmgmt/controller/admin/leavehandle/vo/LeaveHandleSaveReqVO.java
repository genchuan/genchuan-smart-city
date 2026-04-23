package cn.iocoder.yudao.module.studentmgmt.controller.admin.leavehandle.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 离校办理新增/修改 Request VO")
@Data
public class LeaveHandleSaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12005")
    private Long id;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25257")
    @NotNull(message = "学生 ID不能为空")
    private Long studentId;

    @Schema(description = "离校时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "离校时间不能为空")
    private LocalDateTime leaveTime;

    @Schema(description = "离校去处")
    private String leaveAddress;

    @Schema(description = "家长确认时间")
    private LocalDateTime parentConfirmTime;

    @Schema(description = "办理人")
    private String handleUser;

    @Schema(description = "办理时间")
    private LocalDateTime handleTime;

    @Schema(description = "退宿时间")
    private LocalDateTime checkoutTime;

    @Schema(description = "退宿状态：未退宿/已退宿", example = "1")
    private String checkoutStatus;

    @Schema(description = "办理完成率")
    private BigDecimal finishRate;

    @Schema(description = "状态：待确认/待办理/已离校", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "状态：待确认/待办理/已离校不能为空")
    private String status;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}